// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package com.sangnk.grpc.user;

public final class User {
  private User() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_sangnk_UserInput_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_sangnk_UserInput_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_sangnk_UserId_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_sangnk_UserId_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_sangnk_UserMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_sangnk_UserMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_sangnk_DeletedUsers_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_sangnk_DeletedUsers_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nuser.proto\022\006sangnk\032\033google/protobuf/em" +
      "pty.proto\032\036google/protobuf/wrappers.prot" +
      "o\"\031\n\tUserInput\022\014\n\004name\030\001 \001(\t\"\024\n\006UserId\022\n" +
      "\n\002id\030\001 \001(\005\"\'\n\013UserMessage\022\n\n\002id\030\001 \001(\005\022\014\n" +
      "\004name\030\002 \001(\t\"\033\n\014DeletedUsers\022\013\n\003ids\030\001 \003(\005" +
      "2\335\001\n\013UserService\022+\n\006create\022\021.sangnk.User" +
      "Input\032\016.sangnk.UserId\022.\n\007getById\022\016.sangn" +
      "k.UserId\032\023.sangnk.UserMessage\0227\n\006getAll\022" +
      "\026.google.protobuf.Empty\032\023.sangnk.UserMes" +
      "sage0\001\0228\n\016deleteMultiple\022\016.sangnk.UserId",
      "\032\024.sangnk.DeletedUsers(\001B\030\n\024com.sangnk.g" +
      "rpc.userP\001b\006proto3"
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
          com.google.protobuf.EmptyProto.getDescriptor(),
          com.google.protobuf.WrappersProto.getDescriptor(),
        }, assigner);
    internal_static_sangnk_UserInput_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_sangnk_UserInput_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_sangnk_UserInput_descriptor,
        new java.lang.String[] { "Name", });
    internal_static_sangnk_UserId_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_sangnk_UserId_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_sangnk_UserId_descriptor,
        new java.lang.String[] { "Id", });
    internal_static_sangnk_UserMessage_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_sangnk_UserMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_sangnk_UserMessage_descriptor,
        new java.lang.String[] { "Id", "Name", });
    internal_static_sangnk_DeletedUsers_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_sangnk_DeletedUsers_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_sangnk_DeletedUsers_descriptor,
        new java.lang.String[] { "Ids", });
    com.google.protobuf.EmptyProto.getDescriptor();
    com.google.protobuf.WrappersProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
