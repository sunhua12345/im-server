// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MsgLogin.proto

package utouu.im.protobuf.pb;

public final class MsgLogin {
  private MsgLogin() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ReqSdkLoginOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ProtoNet.ReqSdkLogin)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required string utouuImAccount = 1;</code>
     */
    boolean hasUtouuImAccount();
    /**
     * <code>required string utouuImAccount = 1;</code>
     */
    java.lang.String getUtouuImAccount();
    /**
     * <code>required string utouuImAccount = 1;</code>
     */
    com.google.protobuf.ByteString
        getUtouuImAccountBytes();

    /**
     * <code>required string utouuImPwd = 2;</code>
     */
    boolean hasUtouuImPwd();
    /**
     * <code>required string utouuImPwd = 2;</code>
     */
    java.lang.String getUtouuImPwd();
    /**
     * <code>required string utouuImPwd = 2;</code>
     */
    com.google.protobuf.ByteString
        getUtouuImPwdBytes();
  }
  /**
   * Protobuf type {@code ProtoNet.ReqSdkLogin}
   *
   * <pre>
   *REQ_SDK_LOGIN = 100001;//sdk请求登录im服务器
   * </pre>
   */
  public static final class ReqSdkLogin extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:ProtoNet.ReqSdkLogin)
      ReqSdkLoginOrBuilder {
    // Use ReqSdkLogin.newBuilder() to construct.
    private ReqSdkLogin(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ReqSdkLogin(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ReqSdkLogin defaultInstance;
    public static ReqSdkLogin getDefaultInstance() {
      return defaultInstance;
    }

    public ReqSdkLogin getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ReqSdkLogin(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              utouuImAccount_ = bs;
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              utouuImPwd_ = bs;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return utouu.im.protobuf.pb.MsgLogin.internal_static_ProtoNet_ReqSdkLogin_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return utouu.im.protobuf.pb.MsgLogin.internal_static_ProtoNet_ReqSdkLogin_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin.class, utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin.Builder.class);
    }

    public static com.google.protobuf.Parser<ReqSdkLogin> PARSER =
        new com.google.protobuf.AbstractParser<ReqSdkLogin>() {
      public ReqSdkLogin parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ReqSdkLogin(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ReqSdkLogin> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int UTOUUIMACCOUNT_FIELD_NUMBER = 1;
    private java.lang.Object utouuImAccount_;
    /**
     * <code>required string utouuImAccount = 1;</code>
     */
    public boolean hasUtouuImAccount() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string utouuImAccount = 1;</code>
     */
    public java.lang.String getUtouuImAccount() {
      java.lang.Object ref = utouuImAccount_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          utouuImAccount_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string utouuImAccount = 1;</code>
     */
    public com.google.protobuf.ByteString
        getUtouuImAccountBytes() {
      java.lang.Object ref = utouuImAccount_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        utouuImAccount_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int UTOUUIMPWD_FIELD_NUMBER = 2;
    private java.lang.Object utouuImPwd_;
    /**
     * <code>required string utouuImPwd = 2;</code>
     */
    public boolean hasUtouuImPwd() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string utouuImPwd = 2;</code>
     */
    public java.lang.String getUtouuImPwd() {
      java.lang.Object ref = utouuImPwd_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          utouuImPwd_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string utouuImPwd = 2;</code>
     */
    public com.google.protobuf.ByteString
        getUtouuImPwdBytes() {
      java.lang.Object ref = utouuImPwd_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        utouuImPwd_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      utouuImAccount_ = "";
      utouuImPwd_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasUtouuImAccount()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasUtouuImPwd()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getUtouuImAccountBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getUtouuImPwdBytes());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getUtouuImAccountBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getUtouuImPwdBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code ProtoNet.ReqSdkLogin}
     *
     * <pre>
     *REQ_SDK_LOGIN = 100001;//sdk请求登录im服务器
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ProtoNet.ReqSdkLogin)
        utouu.im.protobuf.pb.MsgLogin.ReqSdkLoginOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return utouu.im.protobuf.pb.MsgLogin.internal_static_ProtoNet_ReqSdkLogin_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return utouu.im.protobuf.pb.MsgLogin.internal_static_ProtoNet_ReqSdkLogin_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin.class, utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin.Builder.class);
      }

      // Construct using utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        utouuImAccount_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        utouuImPwd_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return utouu.im.protobuf.pb.MsgLogin.internal_static_ProtoNet_ReqSdkLogin_descriptor;
      }

      public utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin getDefaultInstanceForType() {
        return utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin.getDefaultInstance();
      }

      public utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin build() {
        utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin buildPartial() {
        utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin result = new utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.utouuImAccount_ = utouuImAccount_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.utouuImPwd_ = utouuImPwd_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin) {
          return mergeFrom((utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin other) {
        if (other == utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin.getDefaultInstance()) return this;
        if (other.hasUtouuImAccount()) {
          bitField0_ |= 0x00000001;
          utouuImAccount_ = other.utouuImAccount_;
          onChanged();
        }
        if (other.hasUtouuImPwd()) {
          bitField0_ |= 0x00000002;
          utouuImPwd_ = other.utouuImPwd_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasUtouuImAccount()) {
          
          return false;
        }
        if (!hasUtouuImPwd()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (utouu.im.protobuf.pb.MsgLogin.ReqSdkLogin) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object utouuImAccount_ = "";
      /**
       * <code>required string utouuImAccount = 1;</code>
       */
      public boolean hasUtouuImAccount() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string utouuImAccount = 1;</code>
       */
      public java.lang.String getUtouuImAccount() {
        java.lang.Object ref = utouuImAccount_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            utouuImAccount_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string utouuImAccount = 1;</code>
       */
      public com.google.protobuf.ByteString
          getUtouuImAccountBytes() {
        java.lang.Object ref = utouuImAccount_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          utouuImAccount_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string utouuImAccount = 1;</code>
       */
      public Builder setUtouuImAccount(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        utouuImAccount_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string utouuImAccount = 1;</code>
       */
      public Builder clearUtouuImAccount() {
        bitField0_ = (bitField0_ & ~0x00000001);
        utouuImAccount_ = getDefaultInstance().getUtouuImAccount();
        onChanged();
        return this;
      }
      /**
       * <code>required string utouuImAccount = 1;</code>
       */
      public Builder setUtouuImAccountBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        utouuImAccount_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object utouuImPwd_ = "";
      /**
       * <code>required string utouuImPwd = 2;</code>
       */
      public boolean hasUtouuImPwd() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string utouuImPwd = 2;</code>
       */
      public java.lang.String getUtouuImPwd() {
        java.lang.Object ref = utouuImPwd_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            utouuImPwd_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string utouuImPwd = 2;</code>
       */
      public com.google.protobuf.ByteString
          getUtouuImPwdBytes() {
        java.lang.Object ref = utouuImPwd_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          utouuImPwd_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string utouuImPwd = 2;</code>
       */
      public Builder setUtouuImPwd(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        utouuImPwd_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string utouuImPwd = 2;</code>
       */
      public Builder clearUtouuImPwd() {
        bitField0_ = (bitField0_ & ~0x00000002);
        utouuImPwd_ = getDefaultInstance().getUtouuImPwd();
        onChanged();
        return this;
      }
      /**
       * <code>required string utouuImPwd = 2;</code>
       */
      public Builder setUtouuImPwdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        utouuImPwd_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:ProtoNet.ReqSdkLogin)
    }

    static {
      defaultInstance = new ReqSdkLogin(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:ProtoNet.ReqSdkLogin)
  }

  public interface ResSdkLoginOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ProtoNet.ResSdkLogin)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 loginResult = 1;</code>
     *
     * <pre>
     *0代表登录成功,1代表登录失败,其他枚举后续定义
     * </pre>
     */
    boolean hasLoginResult();
    /**
     * <code>required int32 loginResult = 1;</code>
     *
     * <pre>
     *0代表登录成功,1代表登录失败,其他枚举后续定义
     * </pre>
     */
    int getLoginResult();
  }
  /**
   * Protobuf type {@code ProtoNet.ResSdkLogin}
   *
   * <pre>
   *RES_SDK_LOGIN = 100002;
   * </pre>
   */
  public static final class ResSdkLogin extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:ProtoNet.ResSdkLogin)
      ResSdkLoginOrBuilder {
    // Use ResSdkLogin.newBuilder() to construct.
    private ResSdkLogin(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ResSdkLogin(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ResSdkLogin defaultInstance;
    public static ResSdkLogin getDefaultInstance() {
      return defaultInstance;
    }

    public ResSdkLogin getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ResSdkLogin(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              loginResult_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return utouu.im.protobuf.pb.MsgLogin.internal_static_ProtoNet_ResSdkLogin_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return utouu.im.protobuf.pb.MsgLogin.internal_static_ProtoNet_ResSdkLogin_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              utouu.im.protobuf.pb.MsgLogin.ResSdkLogin.class, utouu.im.protobuf.pb.MsgLogin.ResSdkLogin.Builder.class);
    }

    public static com.google.protobuf.Parser<ResSdkLogin> PARSER =
        new com.google.protobuf.AbstractParser<ResSdkLogin>() {
      public ResSdkLogin parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ResSdkLogin(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ResSdkLogin> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int LOGINRESULT_FIELD_NUMBER = 1;
    private int loginResult_;
    /**
     * <code>required int32 loginResult = 1;</code>
     *
     * <pre>
     *0代表登录成功,1代表登录失败,其他枚举后续定义
     * </pre>
     */
    public boolean hasLoginResult() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 loginResult = 1;</code>
     *
     * <pre>
     *0代表登录成功,1代表登录失败,其他枚举后续定义
     * </pre>
     */
    public int getLoginResult() {
      return loginResult_;
    }

    private void initFields() {
      loginResult_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasLoginResult()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, loginResult_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, loginResult_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static utouu.im.protobuf.pb.MsgLogin.ResSdkLogin parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ResSdkLogin parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ResSdkLogin parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ResSdkLogin parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ResSdkLogin parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ResSdkLogin parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ResSdkLogin parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ResSdkLogin parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ResSdkLogin parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static utouu.im.protobuf.pb.MsgLogin.ResSdkLogin parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(utouu.im.protobuf.pb.MsgLogin.ResSdkLogin prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code ProtoNet.ResSdkLogin}
     *
     * <pre>
     *RES_SDK_LOGIN = 100002;
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ProtoNet.ResSdkLogin)
        utouu.im.protobuf.pb.MsgLogin.ResSdkLoginOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return utouu.im.protobuf.pb.MsgLogin.internal_static_ProtoNet_ResSdkLogin_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return utouu.im.protobuf.pb.MsgLogin.internal_static_ProtoNet_ResSdkLogin_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                utouu.im.protobuf.pb.MsgLogin.ResSdkLogin.class, utouu.im.protobuf.pb.MsgLogin.ResSdkLogin.Builder.class);
      }

      // Construct using utouu.im.protobuf.pb.MsgLogin.ResSdkLogin.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        loginResult_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return utouu.im.protobuf.pb.MsgLogin.internal_static_ProtoNet_ResSdkLogin_descriptor;
      }

      public utouu.im.protobuf.pb.MsgLogin.ResSdkLogin getDefaultInstanceForType() {
        return utouu.im.protobuf.pb.MsgLogin.ResSdkLogin.getDefaultInstance();
      }

      public utouu.im.protobuf.pb.MsgLogin.ResSdkLogin build() {
        utouu.im.protobuf.pb.MsgLogin.ResSdkLogin result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public utouu.im.protobuf.pb.MsgLogin.ResSdkLogin buildPartial() {
        utouu.im.protobuf.pb.MsgLogin.ResSdkLogin result = new utouu.im.protobuf.pb.MsgLogin.ResSdkLogin(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.loginResult_ = loginResult_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof utouu.im.protobuf.pb.MsgLogin.ResSdkLogin) {
          return mergeFrom((utouu.im.protobuf.pb.MsgLogin.ResSdkLogin)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(utouu.im.protobuf.pb.MsgLogin.ResSdkLogin other) {
        if (other == utouu.im.protobuf.pb.MsgLogin.ResSdkLogin.getDefaultInstance()) return this;
        if (other.hasLoginResult()) {
          setLoginResult(other.getLoginResult());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasLoginResult()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        utouu.im.protobuf.pb.MsgLogin.ResSdkLogin parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (utouu.im.protobuf.pb.MsgLogin.ResSdkLogin) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int loginResult_ ;
      /**
       * <code>required int32 loginResult = 1;</code>
       *
       * <pre>
       *0代表登录成功,1代表登录失败,其他枚举后续定义
       * </pre>
       */
      public boolean hasLoginResult() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 loginResult = 1;</code>
       *
       * <pre>
       *0代表登录成功,1代表登录失败,其他枚举后续定义
       * </pre>
       */
      public int getLoginResult() {
        return loginResult_;
      }
      /**
       * <code>required int32 loginResult = 1;</code>
       *
       * <pre>
       *0代表登录成功,1代表登录失败,其他枚举后续定义
       * </pre>
       */
      public Builder setLoginResult(int value) {
        bitField0_ |= 0x00000001;
        loginResult_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 loginResult = 1;</code>
       *
       * <pre>
       *0代表登录成功,1代表登录失败,其他枚举后续定义
       * </pre>
       */
      public Builder clearLoginResult() {
        bitField0_ = (bitField0_ & ~0x00000001);
        loginResult_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:ProtoNet.ResSdkLogin)
    }

    static {
      defaultInstance = new ResSdkLogin(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:ProtoNet.ResSdkLogin)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ProtoNet_ReqSdkLogin_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_ProtoNet_ReqSdkLogin_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ProtoNet_ResSdkLogin_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_ProtoNet_ResSdkLogin_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016MsgLogin.proto\022\010ProtoNet\"9\n\013ReqSdkLogi" +
      "n\022\026\n\016utouuImAccount\030\001 \002(\t\022\022\n\nutouuImPwd\030" +
      "\002 \002(\t\"\"\n\013ResSdkLogin\022\023\n\013loginResult\030\001 \002(" +
      "\005B\026\n\024utouu.im.protobuf.pb"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_ProtoNet_ReqSdkLogin_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ProtoNet_ReqSdkLogin_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_ProtoNet_ReqSdkLogin_descriptor,
        new java.lang.String[] { "UtouuImAccount", "UtouuImPwd", });
    internal_static_ProtoNet_ResSdkLogin_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_ProtoNet_ResSdkLogin_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_ProtoNet_ResSdkLogin_descriptor,
        new java.lang.String[] { "LoginResult", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
