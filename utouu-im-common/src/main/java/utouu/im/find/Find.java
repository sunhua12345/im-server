package utouu.im.find;

public interface Find {
	public void find() throws Exception;
	
	public void findOver();
	
	public void findInit();

	public boolean verification(Class<?> clazz);

	public void start();
}
