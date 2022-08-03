#!/bin/sh
H=/home/andrei/src
SRC=$H/wpn

MCS_GEN_ROOT=$SRC/mcs/gen
QR_ROOT=$SRC/third_party/nayuki/QR-Code-generator
ECEC_ROOT=$SRC/third_party/ecec
RAPIDJSON_ROOT=$SRC/third_party/rapidjson
# wget -c https://github.com/protocolbuffers/protobuf/archive/refs/tags/v3.11.2.tar.gz
#PROTOBUF_ROOT=/home/andrei/src/third_party/grpc/third_party/protobuf/src
# PROTOBUF_ROOT=$H/src/third_party/protobuf-3.13
PROTOBUF_ROOT=$H/../lib/protobuf-3.11.2/src


D=$H/vapidchatter/app/src/main/third_party

mkdir -p $D/wpn
mkdir -p $D/wpn/mcs
cp $SRC/wp-storage-file.h $D/wpn
cp $SRC/notify2string.h $D/wpn
cp $SRC/wp-push.h $D/wpn
cp $SRC/wp-subscribe.h $D/wpn
cp $SRC/wp-connection.h $D/wpn
cp $SRC/sslfactory.h $D/wpn
cp $SRC/mcs/mcsclient.h $D/wpn/mcs
cp $SRC/mcs/heartbeat.h $D/wpn/mcs
cp $SRC/utilqr.h $D/wpn
cp $SRC/utilfile.h $D/wpn
cp $SRC/utilstring.h $D/wpn
cp $SRC/params.h $D/wpn
cp $SRC/commandoutput.h $D/wpn
cp $SRC/endpoint.h $D/wpn
cp $SRC/vapid.h $D/wpn
cp $SRC/utilvapid.h $D/wpn
cp $SRC/utilrecv.h $D/wpn
cp $SRC/utilinstance.h $D/wpn
cp $SRC/wp-registry.h $D/wpn
cp $SRC/utiljson.h $D/wpn
cp $SRC/config-filename.h $D/wpn
cp $SRC/vapid-api.h $D/wpn
cp $SRC/errlist.h $D/wpn
cp $SRC/wpn-notify.h $D/wpn
cp $SRC/platform.h $D/wpn
cp $SRC/wpnapi.h $D/wpn
cp $SRC/onullstream.hpp $D/wpn

cp $SRC/wp-storage-file.cpp $D/wpn
cp $SRC/notify2string.cpp $D/wpn
cp $SRC/wp-push.cpp $D/wpn
cp $SRC/wp-subscribe.cpp $D/wpn
cp $SRC/wp-connection.cpp $D/wpn
cp $SRC/sslfactory.cpp $D/wpn
cp $SRC/mcs/mcsclient.cpp $D/wpn/mcs
cp $SRC/mcs/heartbeat.cpp $D/wpn/mcs
cp $SRC/utilqr.cpp $D/wpn
cp $SRC/utilfile.cpp $D/wpn
cp $SRC/utilstring.cpp $D/wpn
cp $SRC/params.cpp $D/wpn
cp $SRC/commandoutput.cpp $D/wpn
cp $SRC/endpoint.cpp $D/wpn
cp $SRC/vapid.cpp $D/wpn
cp $SRC/utilvapid.cpp $D/wpn
cp $SRC/utilrecv.cpp $D/wpn
cp $SRC/utilinstance.cpp $D/wpn
cp $SRC/wp-registry.cpp $D/wpn
cp $SRC/utiljson.cpp $D/wpn
cp $SRC/config-filename.cpp $D/wpn
cp $SRC/vapid-api.cpp $D/wpn
cp $SRC/errlist.cpp $D/wpn


mkdir -p $D/wpn/mcs/gen
cp $MCS_GEN_ROOT/mcs.pb.h $D/wpn/mcs/gen
cp $MCS_GEN_ROOT/mcs.pb.cc $D/wpn/mcs/gen
cp $MCS_GEN_ROOT/android_checkin.pb.h $D/wpn/mcs/gen
cp $MCS_GEN_ROOT/android_checkin.pb.cc $D/wpn/mcs/gen
cp $MCS_GEN_ROOT/checkin.pb.h $D/wpn/mcs/gen
cp $MCS_GEN_ROOT/checkin.pb.cc $D/wpn/mcs/gen

mkdir -p $D/nayuki/QR-Code-generator
cp $QR_ROOT/BitBuffer.cpp $D/nayuki/QR-Code-generator
cp $QR_ROOT/QrCode.cpp $D/nayuki/QR-Code-generator
cp $QR_ROOT/QrSegment.cpp $D/nayuki/QR-Code-generator
cp $QR_ROOT/BitBuffer.hpp $D/nayuki/QR-Code-generator
cp $QR_ROOT/QrCode.hpp $D/nayuki/QR-Code-generator
cp $QR_ROOT/QrSegment.hpp $D/nayuki/QR-Code-generator

mkdir -p $D/ecec
cp $ECEC_ROOT/ece.h $D/ecec
cp $ECEC_ROOT/base64url.c $D/ecec
cp $ECEC_ROOT/decrypt.c $D/ecec
cp $ECEC_ROOT/encrypt.c $D/ecec
cp $ECEC_ROOT/keys.c $D/ecec
cp $ECEC_ROOT/trailer.c $D/ecec
mkdir -p $D/ecec/ece
cp $ECEC_ROOT/ece/keys.h $D/ecec/ece
cp $ECEC_ROOT/ece/trailer.h $D/ecec/ece


mkdir -p $D/protobuf/google/protobuf
mkdir -p $D/protobuf/google/protobuf/stubs
mkdir -p $D/protobuf/google/protobuf/io
# lite
cp $PROTOBUF_ROOT/google/protobuf/any_lite.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/arena.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/extension_set.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/generated_message_util.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/generated_message_table_driven_lite.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/generated_message_table_driven_lite.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/implicit_weak_message.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/message_lite.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/parse_context.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/repeated_field.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/wire_format_lite.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/stubs/bytestream.cc $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/bytestream.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/common.cc $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/hash.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/int128.cc $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/int128.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/map_util.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/mathutil.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/status.cc $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/status.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/status_macros.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/statusor.cc $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/statusor.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/stringpiece.cc $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/stringpiece.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/stringprintf.cc $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/stringprintf.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/structurally_valid.cc $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/strutil.cc $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/time.cc $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/time.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/io/io_win32.cc $D/protobuf/google/protobuf/io
cp $PROTOBUF_ROOT/google/protobuf/io/io_win32.h $D/protobuf/google/protobuf/io
cp $PROTOBUF_ROOT/google/protobuf/io/coded_stream.cc $D/protobuf/google/protobuf/io
cp $PROTOBUF_ROOT/google/protobuf/io/strtod.cc $D/protobuf/google/protobuf/io
cp $PROTOBUF_ROOT/google/protobuf/io/zero_copy_stream.cc $D/protobuf/google/protobuf/io
cp $PROTOBUF_ROOT/google/protobuf/io/zero_copy_stream_impl_lite.cc $D/protobuf/google/protobuf/io
cp $PROTOBUF_ROOT/google/protobuf/generated_enum_util.cc $D/protobuf/google/protobuf
# others
mkdir -p $D/protobuf/google/protobuf/compiler
mkdir -p $D/protobuf/google/protobuf/util
mkdir -p $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/any.pb.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/api.pb.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/any.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/descriptor.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/descriptor_database.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/descriptor.pb.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/duration.pb.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/dynamic_message.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/empty.pb.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/extension_set_heavy.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/field_mask.pb.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/generated_message_reflection.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/generated_message_table_driven_lite.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/generated_message_table_driven.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/map_field.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/message.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/message.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/reflection_internal.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/reflection_ops.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/service.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/source_context.pb.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/struct.pb.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/text_format.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/timestamp.pb.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/type.pb.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/unknown_field_set.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/wire_format.cc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/wrappers.pb.cc $D/protobuf/google/protobuf

cp $PROTOBUF_ROOT/google/protobuf/compiler/importer.cc $D/protobuf/google/protobuf/compiler
cp $PROTOBUF_ROOT/google/protobuf/compiler/parser.h $D/protobuf/google/protobuf/compiler
cp $PROTOBUF_ROOT/google/protobuf/compiler/parser.cc $D/protobuf/google/protobuf/compiler
cp $PROTOBUF_ROOT/google/protobuf/compiler/importer.h $D/protobuf/google/protobuf/compiler

cp $PROTOBUF_ROOT/google/protobuf/util/delimited_message_util.cc $D/protobuf/google/protobuf/util
cp $PROTOBUF_ROOT/google/protobuf/util/field_comparator.cc $D/protobuf/google/protobuf/util
cp $PROTOBUF_ROOT/google/protobuf/util/field_mask_util.cc $D/protobuf/google/protobuf/util
cp $PROTOBUF_ROOT/google/protobuf/util/json_util.cc $D/protobuf/google/protobuf/util
cp $PROTOBUF_ROOT/google/protobuf/util/message_differencer.cc $D/protobuf/google/protobuf/util
cp $PROTOBUF_ROOT/google/protobuf/util/time_util.cc $D/protobuf/google/protobuf/util
cp $PROTOBUF_ROOT/google/protobuf/util/type_resolver_util.cc $D/protobuf/google/protobuf/util
cp $PROTOBUF_ROOT/google/protobuf/util/type_resolver.h $D/protobuf/google/protobuf/util
cp $PROTOBUF_ROOT/google/protobuf/util/field_comparator.h $D/protobuf/google/protobuf/util
cp $PROTOBUF_ROOT/google/protobuf/util/message_differencer.h $D/protobuf/google/protobuf/util
cp $PROTOBUF_ROOT/google/protobuf/util/field_mask_util.h $D/protobuf/google/protobuf/util
cp $PROTOBUF_ROOT/google/protobuf/util/delimited_message_util.h $D/protobuf/google/protobuf/util
cp $PROTOBUF_ROOT/google/protobuf/util/json_util.h $D/protobuf/google/protobuf/util
cp $PROTOBUF_ROOT/google/protobuf/util/type_resolver_util.h $D/protobuf/google/protobuf/util
cp $PROTOBUF_ROOT/google/protobuf/util/time_util.h $D/protobuf/google/protobuf/util


cp $PROTOBUF_ROOT/google/protobuf/util/internal/constants.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/datapiece.cc $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/datapiece.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/default_value_objectwriter.cc $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/default_value_objectwriter.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/error_listener.cc $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/error_listener.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/expecting_objectwriter.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/field_mask_utility.cc $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/field_mask_utility.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/json_escaping.cc $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/json_escaping.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/json_objectwriter.cc $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/json_objectwriter.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/json_stream_parser.cc $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/json_stream_parser.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/location_tracker.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/mock_error_listener.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/object_location_tracker.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/object_source.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/object_writer.cc $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/object_writer.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/protostream_objectsource.cc $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/protostream_objectsource.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/protostream_objectwriter.cc $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/protostream_objectwriter.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/proto_writer.cc $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/proto_writer.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/structured_objectwriter.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/type_info.cc $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/type_info.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/type_info_test_helper.cc $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/type_info_test_helper.h $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/utility.cc $D/protobuf/google/protobuf/util/internal
cp $PROTOBUF_ROOT/google/protobuf/util/internal/utility.h $D/protobuf/google/protobuf/util/internal


cp $PROTOBUF_ROOT/google/protobuf/port_def.inc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/port_undef.inc $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/arena.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/arena_impl.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/arenastring.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/descriptor.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/port.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/generated_message_table_driven.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/generated_enum_util.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/generated_message_reflection.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/generated_enum_reflection.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/map.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/message_lite.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/metadata_lite.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/map_type_handler.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/unknown_field_set.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/parse_context.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/implicit_weak_message.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/repeated_field.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/wire_format_lite.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/map_entry_lite.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/text_format.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/generated_message_util.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/any.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/has_bits.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/map_field_lite.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/inlined_string_field.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/extension_set.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/extension_set_inl.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/any.pb.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/api.pb.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/reflection_ops.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/wire_format.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/source_context.pb.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/type.pb.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/descriptor.pb.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/descriptor_database.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/duration.pb.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/dynamic_message.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/reflection.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/map_field.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/empty.pb.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/map_entry.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/map_field_inl.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/field_mask.pb.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/service.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/struct.pb.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/timestamp.pb.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/wrappers.pb.h $D/protobuf/google/protobuf
cp $PROTOBUF_ROOT/google/protobuf/metadata.h $D/protobuf/google/protobuf


cp $PROTOBUF_ROOT/google/protobuf/io/gzip_stream.cc $D/protobuf/google/protobuf/io
cp $PROTOBUF_ROOT/google/protobuf/io/printer.cc $D/protobuf/google/protobuf/io
cp $PROTOBUF_ROOT/google/protobuf/io/tokenizer.cc $D/protobuf/google/protobuf/io
cp $PROTOBUF_ROOT/google/protobuf/io/zero_copy_stream_impl.cc $D/protobuf/google/protobuf/io
cp $PROTOBUF_ROOT/google/protobuf/io/coded_stream.h $D/protobuf/google/protobuf/io
cp $PROTOBUF_ROOT/google/protobuf/io/zero_copy_stream_impl_lite.h $D/protobuf/google/protobuf/io
cp $PROTOBUF_ROOT/google/protobuf/io/zero_copy_stream.h $D/protobuf/google/protobuf/io
cp $PROTOBUF_ROOT/google/protobuf/io/zero_copy_stream_impl.h $D/protobuf/google/protobuf/io
cp $PROTOBUF_ROOT/google/protobuf/io/strtod.h $D/protobuf/google/protobuf/io
cp $PROTOBUF_ROOT/google/protobuf/io/tokenizer.h $D/protobuf/google/protobuf/io
cp $PROTOBUF_ROOT/google/protobuf/io/gzip_stream.h $D/protobuf/google/protobuf/io
cp $PROTOBUF_ROOT/google/protobuf/io/printer.h $D/protobuf/google/protobuf/io


cp $PROTOBUF_ROOT/google/protobuf/stubs/callback.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/stl_util.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/common.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/macros.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/port.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/platform_macros.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/casts.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/fastmem.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/logging.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/mutex.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/once.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/strutil.h $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/substitute.cc $D/protobuf/google/protobuf/stubs
cp $PROTOBUF_ROOT/google/protobuf/stubs/substitute.h $D/protobuf/google/protobuf/stubs

mkdir -p $D/rapidjson
mkdir -p $D/rapidjson/error
mkdir -p $D/rapidjson/internal
mkdir -p $D/rapidjson/msinttypes
cp $RAPIDJSON_ROOT/allocators.h $D/rapidjson
cp $RAPIDJSON_ROOT/istreamwrapper.h $D/rapidjson
cp $RAPIDJSON_ROOT/ostreamwrapper.h $D/rapidjson
cp $RAPIDJSON_ROOT/schema.h $D/rapidjson
cp $RAPIDJSON_ROOT/cursorstreamwrapper.h $D/rapidjson
cp $RAPIDJSON_ROOT/filereadstream.h $D/rapidjson
cp $RAPIDJSON_ROOT/pointer.h $D/rapidjson
cp $RAPIDJSON_ROOT/stream.h $D/rapidjson
cp $RAPIDJSON_ROOT/document.h $D/rapidjson
cp $RAPIDJSON_ROOT/filewritestream.h $D/rapidjson
cp $RAPIDJSON_ROOT/memorybuffer.h $D/rapidjson
cp $RAPIDJSON_ROOT/prettywriter.h $D/rapidjson
cp $RAPIDJSON_ROOT/stringbuffer.h $D/rapidjson
cp $RAPIDJSON_ROOT/encodedstream.h $D/rapidjson
cp $RAPIDJSON_ROOT/fwd.h $D/rapidjson
cp $RAPIDJSON_ROOT/memorystream.h $D/rapidjson
cp $RAPIDJSON_ROOT/rapidjson.h $D/rapidjson
cp $RAPIDJSON_ROOT/writer.h $D/rapidjson
cp $RAPIDJSON_ROOT/encodings.h $D/rapidjson
cp $RAPIDJSON_ROOT/reader.h $D/rapidjson

cp $RAPIDJSON_ROOT/error/en.h $D/rapidjson/error
cp $RAPIDJSON_ROOT/error/error.h $D/rapidjson/error

cp $RAPIDJSON_ROOT/internal/biginteger.h $D/rapidjson/internal
cp $RAPIDJSON_ROOT/internal/dtoa.h $D/rapidjson/internal
cp $RAPIDJSON_ROOT/internal/itoa.h $D/rapidjson/internal
cp $RAPIDJSON_ROOT/internal/pow10.h $D/rapidjson/internal
cp $RAPIDJSON_ROOT/internal/stack.h $D/rapidjson/internal
cp $RAPIDJSON_ROOT/internal/strtod.h $D/rapidjson/internal
cp $RAPIDJSON_ROOT/internal/diyfp.h $D/rapidjson/internal
cp $RAPIDJSON_ROOT/internal/ieee754.h $D/rapidjson/internal
cp $RAPIDJSON_ROOT/internal/meta.h $D/rapidjson/internal
cp $RAPIDJSON_ROOT/internal/regex.h $D/rapidjson/internal
cp $RAPIDJSON_ROOT/internal/strfunc.h $D/rapidjson/internal
cp $RAPIDJSON_ROOT/internal/swap.h $D/rapidjson/internal

cp $RAPIDJSON_ROOT/msinttypes/inttypes.h $D/rapidjson/msinttypes
cp $RAPIDJSON_ROOT/msinttypes/stdint.h $D/rapidjson/msinttypes
