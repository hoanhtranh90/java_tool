// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package com.sangnk.grpc.user;

/**
 * Protobuf type {@code sangnk.GetAllUsersResponseBlock}
 */
public  final class GetAllUsersResponseBlock extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:sangnk.GetAllUsersResponseBlock)
    GetAllUsersResponseBlockOrBuilder {
  // Use GetAllUsersResponseBlock.newBuilder() to construct.
  private GetAllUsersResponseBlock(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetAllUsersResponseBlock() {
    users_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private GetAllUsersResponseBlock(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              users_ = new java.util.ArrayList<com.sangnk.grpc.user.UserMessage>();
              mutable_bitField0_ |= 0x00000001;
            }
            users_.add(
                input.readMessage(com.sangnk.grpc.user.UserMessage.parser(), extensionRegistry));
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        users_ = java.util.Collections.unmodifiableList(users_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.sangnk.grpc.user.User.internal_static_sangnk_GetAllUsersResponseBlock_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.sangnk.grpc.user.User.internal_static_sangnk_GetAllUsersResponseBlock_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.sangnk.grpc.user.GetAllUsersResponseBlock.class, com.sangnk.grpc.user.GetAllUsersResponseBlock.Builder.class);
  }

  public static final int USERS_FIELD_NUMBER = 1;
  private java.util.List<com.sangnk.grpc.user.UserMessage> users_;
  /**
   * <code>repeated .sangnk.UserMessage users = 1;</code>
   */
  public java.util.List<com.sangnk.grpc.user.UserMessage> getUsersList() {
    return users_;
  }
  /**
   * <code>repeated .sangnk.UserMessage users = 1;</code>
   */
  public java.util.List<? extends com.sangnk.grpc.user.UserMessageOrBuilder> 
      getUsersOrBuilderList() {
    return users_;
  }
  /**
   * <code>repeated .sangnk.UserMessage users = 1;</code>
   */
  public int getUsersCount() {
    return users_.size();
  }
  /**
   * <code>repeated .sangnk.UserMessage users = 1;</code>
   */
  public com.sangnk.grpc.user.UserMessage getUsers(int index) {
    return users_.get(index);
  }
  /**
   * <code>repeated .sangnk.UserMessage users = 1;</code>
   */
  public com.sangnk.grpc.user.UserMessageOrBuilder getUsersOrBuilder(
      int index) {
    return users_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < users_.size(); i++) {
      output.writeMessage(1, users_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < users_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, users_.get(i));
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.sangnk.grpc.user.GetAllUsersResponseBlock)) {
      return super.equals(obj);
    }
    com.sangnk.grpc.user.GetAllUsersResponseBlock other = (com.sangnk.grpc.user.GetAllUsersResponseBlock) obj;

    boolean result = true;
    result = result && getUsersList()
        .equals(other.getUsersList());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getUsersCount() > 0) {
      hash = (37 * hash) + USERS_FIELD_NUMBER;
      hash = (53 * hash) + getUsersList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.sangnk.grpc.user.GetAllUsersResponseBlock parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.sangnk.grpc.user.GetAllUsersResponseBlock parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.sangnk.grpc.user.GetAllUsersResponseBlock parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.sangnk.grpc.user.GetAllUsersResponseBlock parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.sangnk.grpc.user.GetAllUsersResponseBlock parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.sangnk.grpc.user.GetAllUsersResponseBlock parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.sangnk.grpc.user.GetAllUsersResponseBlock parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.sangnk.grpc.user.GetAllUsersResponseBlock parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.sangnk.grpc.user.GetAllUsersResponseBlock parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.sangnk.grpc.user.GetAllUsersResponseBlock parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.sangnk.grpc.user.GetAllUsersResponseBlock parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.sangnk.grpc.user.GetAllUsersResponseBlock parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.sangnk.grpc.user.GetAllUsersResponseBlock prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code sangnk.GetAllUsersResponseBlock}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:sangnk.GetAllUsersResponseBlock)
      com.sangnk.grpc.user.GetAllUsersResponseBlockOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.sangnk.grpc.user.User.internal_static_sangnk_GetAllUsersResponseBlock_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.sangnk.grpc.user.User.internal_static_sangnk_GetAllUsersResponseBlock_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.sangnk.grpc.user.GetAllUsersResponseBlock.class, com.sangnk.grpc.user.GetAllUsersResponseBlock.Builder.class);
    }

    // Construct using com.sangnk.grpc.user.GetAllUsersResponseBlock.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getUsersFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (usersBuilder_ == null) {
        users_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        usersBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.sangnk.grpc.user.User.internal_static_sangnk_GetAllUsersResponseBlock_descriptor;
    }

    public com.sangnk.grpc.user.GetAllUsersResponseBlock getDefaultInstanceForType() {
      return com.sangnk.grpc.user.GetAllUsersResponseBlock.getDefaultInstance();
    }

    public com.sangnk.grpc.user.GetAllUsersResponseBlock build() {
      com.sangnk.grpc.user.GetAllUsersResponseBlock result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.sangnk.grpc.user.GetAllUsersResponseBlock buildPartial() {
      com.sangnk.grpc.user.GetAllUsersResponseBlock result = new com.sangnk.grpc.user.GetAllUsersResponseBlock(this);
      int from_bitField0_ = bitField0_;
      if (usersBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          users_ = java.util.Collections.unmodifiableList(users_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.users_ = users_;
      } else {
        result.users_ = usersBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.sangnk.grpc.user.GetAllUsersResponseBlock) {
        return mergeFrom((com.sangnk.grpc.user.GetAllUsersResponseBlock)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.sangnk.grpc.user.GetAllUsersResponseBlock other) {
      if (other == com.sangnk.grpc.user.GetAllUsersResponseBlock.getDefaultInstance()) return this;
      if (usersBuilder_ == null) {
        if (!other.users_.isEmpty()) {
          if (users_.isEmpty()) {
            users_ = other.users_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureUsersIsMutable();
            users_.addAll(other.users_);
          }
          onChanged();
        }
      } else {
        if (!other.users_.isEmpty()) {
          if (usersBuilder_.isEmpty()) {
            usersBuilder_.dispose();
            usersBuilder_ = null;
            users_ = other.users_;
            bitField0_ = (bitField0_ & ~0x00000001);
            usersBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getUsersFieldBuilder() : null;
          } else {
            usersBuilder_.addAllMessages(other.users_);
          }
        }
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.sangnk.grpc.user.GetAllUsersResponseBlock parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.sangnk.grpc.user.GetAllUsersResponseBlock) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<com.sangnk.grpc.user.UserMessage> users_ =
      java.util.Collections.emptyList();
    private void ensureUsersIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        users_ = new java.util.ArrayList<com.sangnk.grpc.user.UserMessage>(users_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.sangnk.grpc.user.UserMessage, com.sangnk.grpc.user.UserMessage.Builder, com.sangnk.grpc.user.UserMessageOrBuilder> usersBuilder_;

    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public java.util.List<com.sangnk.grpc.user.UserMessage> getUsersList() {
      if (usersBuilder_ == null) {
        return java.util.Collections.unmodifiableList(users_);
      } else {
        return usersBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public int getUsersCount() {
      if (usersBuilder_ == null) {
        return users_.size();
      } else {
        return usersBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public com.sangnk.grpc.user.UserMessage getUsers(int index) {
      if (usersBuilder_ == null) {
        return users_.get(index);
      } else {
        return usersBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public Builder setUsers(
        int index, com.sangnk.grpc.user.UserMessage value) {
      if (usersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureUsersIsMutable();
        users_.set(index, value);
        onChanged();
      } else {
        usersBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public Builder setUsers(
        int index, com.sangnk.grpc.user.UserMessage.Builder builderForValue) {
      if (usersBuilder_ == null) {
        ensureUsersIsMutable();
        users_.set(index, builderForValue.build());
        onChanged();
      } else {
        usersBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public Builder addUsers(com.sangnk.grpc.user.UserMessage value) {
      if (usersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureUsersIsMutable();
        users_.add(value);
        onChanged();
      } else {
        usersBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public Builder addUsers(
        int index, com.sangnk.grpc.user.UserMessage value) {
      if (usersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureUsersIsMutable();
        users_.add(index, value);
        onChanged();
      } else {
        usersBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public Builder addUsers(
        com.sangnk.grpc.user.UserMessage.Builder builderForValue) {
      if (usersBuilder_ == null) {
        ensureUsersIsMutable();
        users_.add(builderForValue.build());
        onChanged();
      } else {
        usersBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public Builder addUsers(
        int index, com.sangnk.grpc.user.UserMessage.Builder builderForValue) {
      if (usersBuilder_ == null) {
        ensureUsersIsMutable();
        users_.add(index, builderForValue.build());
        onChanged();
      } else {
        usersBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public Builder addAllUsers(
        java.lang.Iterable<? extends com.sangnk.grpc.user.UserMessage> values) {
      if (usersBuilder_ == null) {
        ensureUsersIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, users_);
        onChanged();
      } else {
        usersBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public Builder clearUsers() {
      if (usersBuilder_ == null) {
        users_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        usersBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public Builder removeUsers(int index) {
      if (usersBuilder_ == null) {
        ensureUsersIsMutable();
        users_.remove(index);
        onChanged();
      } else {
        usersBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public com.sangnk.grpc.user.UserMessage.Builder getUsersBuilder(
        int index) {
      return getUsersFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public com.sangnk.grpc.user.UserMessageOrBuilder getUsersOrBuilder(
        int index) {
      if (usersBuilder_ == null) {
        return users_.get(index);  } else {
        return usersBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public java.util.List<? extends com.sangnk.grpc.user.UserMessageOrBuilder> 
         getUsersOrBuilderList() {
      if (usersBuilder_ != null) {
        return usersBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(users_);
      }
    }
    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public com.sangnk.grpc.user.UserMessage.Builder addUsersBuilder() {
      return getUsersFieldBuilder().addBuilder(
          com.sangnk.grpc.user.UserMessage.getDefaultInstance());
    }
    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public com.sangnk.grpc.user.UserMessage.Builder addUsersBuilder(
        int index) {
      return getUsersFieldBuilder().addBuilder(
          index, com.sangnk.grpc.user.UserMessage.getDefaultInstance());
    }
    /**
     * <code>repeated .sangnk.UserMessage users = 1;</code>
     */
    public java.util.List<com.sangnk.grpc.user.UserMessage.Builder> 
         getUsersBuilderList() {
      return getUsersFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.sangnk.grpc.user.UserMessage, com.sangnk.grpc.user.UserMessage.Builder, com.sangnk.grpc.user.UserMessageOrBuilder> 
        getUsersFieldBuilder() {
      if (usersBuilder_ == null) {
        usersBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.sangnk.grpc.user.UserMessage, com.sangnk.grpc.user.UserMessage.Builder, com.sangnk.grpc.user.UserMessageOrBuilder>(
                users_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        users_ = null;
      }
      return usersBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:sangnk.GetAllUsersResponseBlock)
  }

  // @@protoc_insertion_point(class_scope:sangnk.GetAllUsersResponseBlock)
  private static final com.sangnk.grpc.user.GetAllUsersResponseBlock DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.sangnk.grpc.user.GetAllUsersResponseBlock();
  }

  public static com.sangnk.grpc.user.GetAllUsersResponseBlock getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetAllUsersResponseBlock>
      PARSER = new com.google.protobuf.AbstractParser<GetAllUsersResponseBlock>() {
    public GetAllUsersResponseBlock parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GetAllUsersResponseBlock(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetAllUsersResponseBlock> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetAllUsersResponseBlock> getParserForType() {
    return PARSER;
  }

  public com.sangnk.grpc.user.GetAllUsersResponseBlock getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
