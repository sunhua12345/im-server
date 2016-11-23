package utouu.im.thread;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utouu.im.find.DynamicFind;


public class WorkManager extends DynamicFind{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private final Map<String, Class<? extends Work>> allWorks = new ConcurrentHashMap<String, Class<? extends Work>>();
	private static WorkManager workManager = new WorkManager();
	private final Map<WorkQueue, Worker> workers;
	private final ExecutorService executor;

	public static WorkManager getManager() {
		return workManager;
	}

	private WorkManager() {
		this.executor = Executors.newCachedThreadPool();
		this.workers = new ConcurrentHashMap<WorkQueue, Worker>();
	}

	public void submit(Class<? extends Work> workClass, Object... objs) {
		try {
			Class<? extends Work> clz = allWorks.get(workClass.getSimpleName());
			if (clz == null) {
				allWorks.put(workClass.getSimpleName(), workClass);
				clz = workClass;
			}
			Work work = clz.newInstance();
			work.init(objs);
			submit(work);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void submit(Work work) {
		if (work instanceof AynWork) {
			AynWork aynWork = (AynWork) work;
			executor.submit(aynWork);
		} else if (work instanceof QueueWork) {
			QueueWork queueWork = (QueueWork) work;
			WorkQueue workQueue = queueWork.getWorkQueue();

			synchronized (workers) {
				Worker worker = workers.get(workQueue);
				if (worker == null) {
					worker = createWorker(workQueue);
					worker.getQueueWorks().offer(queueWork);
					executor.submit(worker);
					return;
				}
				worker.getQueueWorks().offer(queueWork);
				// logger.debug("ofer work to {},size:{}--------", workQueue,
				// worker.getQueueWorks().size());
			}
		} else {
			System.out.println(work.getClass()
					.getSimpleName()+" work's type can not found");
		}
	}

	private Worker createWorker(WorkQueue workQueue) {
		Worker worker = null;
		worker = new Worker(workQueue);
		workers.put(workQueue, worker);
		return worker;
	}

	private class Worker extends AynWork {
		private static final long serialVersionUID = 5492935692105924020L;
		private final WorkQueue workQueue;
		private final LinkedBlockingDeque<QueueWork> queueWorks;

		@SuppressWarnings("unused")
		public WorkQueue getWorkQueue() {
			return workQueue;
		}

		public Worker(WorkQueue workQueue) {
			this.workQueue = workQueue;
			queueWorks = new LinkedBlockingDeque<QueueWork>();
		}

		public LinkedBlockingDeque<QueueWork> getQueueWorks() {
			return queueWorks;
		}

		public void init(Object... objs) throws Exception {

		}

		public void run() {
			while (true) {
				QueueWork queueWork = null;
				try {
					queueWork = queueWorks.take();
					queueWork.run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public void findClass(Class<?> clazz) throws Exception {
		Class<? extends Work> clz = (Class<? extends Work>) clazz;
		this.allWorks.put(clazz.getSimpleName(), clz);
	}

	public boolean verification(Class<?> clazz) {
		return superClassOn(clazz, QueueWork.class)
				|| superClassOn(clazz, AynWork.class)
				|| superClassOn(clazz, TimeWork.class);
	}

	public <T> T submitCallable(Class<? extends Callable<T>> clz,
			Object... objs) throws Exception {
		Callable<T> callable = clz.newInstance();
		if (callable instanceof Work) {
			Work work = (Work) callable;
			work.init(objs);
			@SuppressWarnings({ "rawtypes", "unchecked" })
			Future<T> future = this.executor.submit((Callable) work);
			return future.get();
		} else {
			throw new RuntimeException("must implements Work interface!!!");
		}
	}

	@Override
	public void findOver() {
		
	}

	@Override
	public void findInit() {
		// TODO Auto-generated method stub
		
	}
}
