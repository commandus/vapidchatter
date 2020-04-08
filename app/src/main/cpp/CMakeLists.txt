cmake_minimum_required(VERSION 3.4.1)

set (OPENSSL_INC /home/andrei/lib/openssl-1.1.1f/include)
set (PROTOBUF_ROOT /home/andrei/lib/grpc/third_party/protobuf/src)
set (WPN_ROOT /home/andrei/src/wpn)
set (OPENSSL_ROOT /home/andrei/lib/openssl-1.1.1f)
set (OPENSSL_INC ${OPENSSL_ROOT}/include)
set (CURL_ROOT /home/andrei/lib/curl-7.69.1)
set (CURL_INC ${CURL_ROOT}/include)

set (MCS_GEN_ROOT ${WPN_ROOT}/mcs/gen)
set (ECEC_ROOT ${WPN_ROOT}/third_party/ecec)
set (QR_ROOT ${WPN_ROOT}/third_party/nayuki/QR-Code-generator)

set (PROTOS ${WPN_ROOT}/mcs/mcs.proto ${WPN_ROOT}/mcs/android_checkin.proto
        ${WPN_ROOT}/mcs/checkin.proto)
set (EXTGENMCS ${WPN_ROOT}/mcs.pb.h ${WPN_ROOT}/android_checkin.pb.h ${WPN_ROOT}/checkin.pb.h
        ${WPN_ROOT}/mcs.pb.cc ${WPN_ROOT}/android_checkin.pb.cc ${WPN_ROOT}/checkin.pb.cc)
add_custom_command(
        OUTPUT
            ${MCS_GEN_ROOT}/mcs.pb.cc
            ${MCS_GEN_ROOT}/android_checkin.pb.cc
            ${MCS_GEN_ROOT}/checkin.pb.cc
        COMMAND protoc -I ${WPN_ROOT}/mcs --cpp_out=. ${PROTOS} && mv ${EXTGENMCS} mcs/gen
        WORKING_DIRECTORY ${WPN_ROOT}
)
include_directories(${PROTOBUF_ROOT} ${OPENSSL_INC}
        ${WPN_ROOT} ${WPN_ROOT}/third_party ${WPN_ROOT}/third_party/ecec ${MCS_GEN_ROOT}
        ${OPENSSL_INC} ${CURL_INC} ${CURL_ECEC_ROOT})
set(PROTO_LITE_SRC
        ${PROTOBUF_ROOT}/google/protobuf/stubs/bytestream.cc
        ${PROTOBUF_ROOT}/google/protobuf/stubs/bytestream.h
        ${PROTOBUF_ROOT}/google/protobuf/stubs/common.cc
        ${PROTOBUF_ROOT}/google/protobuf/stubs/hash.h
        ${PROTOBUF_ROOT}/google/protobuf/stubs/int128.cc
        ${PROTOBUF_ROOT}/google/protobuf/stubs/int128.h
        ${PROTOBUF_ROOT}/google/protobuf/io/io_win32.cc
        ${PROTOBUF_ROOT}/google/protobuf/io/io_win32.h
        ${PROTOBUF_ROOT}/google/protobuf/stubs/map_util.h
        ${PROTOBUF_ROOT}/google/protobuf/stubs/mathutil.h
        ${PROTOBUF_ROOT}/google/protobuf/stubs/status.cc
        ${PROTOBUF_ROOT}/google/protobuf/stubs/status.h
        ${PROTOBUF_ROOT}/google/protobuf/stubs/status_macros.h
        ${PROTOBUF_ROOT}/google/protobuf/stubs/statusor.cc
        ${PROTOBUF_ROOT}/google/protobuf/stubs/statusor.h
        ${PROTOBUF_ROOT}/google/protobuf/stubs/stringpiece.cc
        ${PROTOBUF_ROOT}/google/protobuf/stubs/stringpiece.h
        ${PROTOBUF_ROOT}/google/protobuf/stubs/stringprintf.cc
        ${PROTOBUF_ROOT}/google/protobuf/stubs/stringprintf.h
        ${PROTOBUF_ROOT}/google/protobuf/stubs/structurally_valid.cc
        ${PROTOBUF_ROOT}/google/protobuf/stubs/strutil.cc
        ${PROTOBUF_ROOT}/google/protobuf/stubs/time.cc
        ${PROTOBUF_ROOT}/google/protobuf/stubs/time.h
        ${PROTOBUF_ROOT}/google/protobuf/any_lite.cc
        ${PROTOBUF_ROOT}/google/protobuf/arena.cc
        ${PROTOBUF_ROOT}/google/protobuf/extension_set.cc
        ${PROTOBUF_ROOT}/google/protobuf/generated_message_util.cc
        ${PROTOBUF_ROOT}/google/protobuf/generated_message_table_driven_lite.h
        ${PROTOBUF_ROOT}/google/protobuf/generated_message_table_driven_lite.cc
        ${PROTOBUF_ROOT}/google/protobuf/implicit_weak_message.cc
        ${PROTOBUF_ROOT}/google/protobuf/message_lite.cc
        ${PROTOBUF_ROOT}/google/protobuf/parse_context.cc
        ${PROTOBUF_ROOT}/google/protobuf/repeated_field.cc
        ${PROTOBUF_ROOT}/google/protobuf/wire_format_lite.cc
        ${PROTOBUF_ROOT}/google/protobuf/io/coded_stream.cc
        ${PROTOBUF_ROOT}/google/protobuf/io/strtod.cc
        ${PROTOBUF_ROOT}/google/protobuf/io/zero_copy_stream.cc
        ${PROTOBUF_ROOT}/google/protobuf/io/zero_copy_stream_impl_lite.cc
        )

#  ${PROTOBUF_ROOT}/google/protobuf/io/coded_stream_inl.h

set(PROTO_SRC
        ${PROTO_LITE_SRC}
        ${PROTOBUF_ROOT}/google/protobuf/any.pb.cc
        ${PROTOBUF_ROOT}/google/protobuf/api.pb.cc
        ${PROTOBUF_ROOT}/google/protobuf/any.cc
        ${PROTOBUF_ROOT}/google/protobuf/descriptor.cc
        ${PROTOBUF_ROOT}/google/protobuf/descriptor_database.cc
        ${PROTOBUF_ROOT}/google/protobuf/descriptor.pb.cc
        ${PROTOBUF_ROOT}/google/protobuf/duration.pb.cc
        ${PROTOBUF_ROOT}/google/protobuf/dynamic_message.cc
        ${PROTOBUF_ROOT}/google/protobuf/empty.pb.cc
        ${PROTOBUF_ROOT}/google/protobuf/extension_set_heavy.cc
        ${PROTOBUF_ROOT}/google/protobuf/field_mask.pb.cc
        ${PROTOBUF_ROOT}/google/protobuf/generated_message_reflection.cc
        ${PROTOBUF_ROOT}/google/protobuf/generated_message_table_driven_lite.h
        ${PROTOBUF_ROOT}/google/protobuf/generated_message_table_driven.cc
        ${PROTOBUF_ROOT}/google/protobuf/map_field.cc
        ${PROTOBUF_ROOT}/google/protobuf/message.cc
        ${PROTOBUF_ROOT}/google/protobuf/reflection_internal.h
        ${PROTOBUF_ROOT}/google/protobuf/reflection_ops.cc
        ${PROTOBUF_ROOT}/google/protobuf/service.cc
        ${PROTOBUF_ROOT}/google/protobuf/source_context.pb.cc
        ${PROTOBUF_ROOT}/google/protobuf/struct.pb.cc
        ${PROTOBUF_ROOT}/google/protobuf/stubs/substitute.cc
        ${PROTOBUF_ROOT}/google/protobuf/stubs/substitute.h
        ${PROTOBUF_ROOT}/google/protobuf/text_format.cc
        ${PROTOBUF_ROOT}/google/protobuf/timestamp.pb.cc
        ${PROTOBUF_ROOT}/google/protobuf/type.pb.cc
        ${PROTOBUF_ROOT}/google/protobuf/unknown_field_set.cc
        ${PROTOBUF_ROOT}/google/protobuf/wire_format.cc
        ${PROTOBUF_ROOT}/google/protobuf/wrappers.pb.cc
        ${PROTOBUF_ROOT}/google/protobuf/io/gzip_stream.cc
        ${PROTOBUF_ROOT}/google/protobuf/io/printer.cc
        ${PROTOBUF_ROOT}/google/protobuf/io/tokenizer.cc
        ${PROTOBUF_ROOT}/google/protobuf/io/zero_copy_stream_impl.cc
        ${PROTOBUF_ROOT}/google/protobuf/compiler/importer.cc
        ${PROTOBUF_ROOT}/google/protobuf/compiler/parser.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/delimited_message_util.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/field_comparator.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/field_mask_util.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/constants.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/datapiece.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/datapiece.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/default_value_objectwriter.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/default_value_objectwriter.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/error_listener.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/error_listener.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/expecting_objectwriter.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/field_mask_utility.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/field_mask_utility.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/json_escaping.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/json_escaping.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/json_objectwriter.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/json_objectwriter.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/json_stream_parser.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/json_stream_parser.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/location_tracker.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/mock_error_listener.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/object_location_tracker.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/object_source.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/object_writer.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/object_writer.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/protostream_objectsource.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/protostream_objectsource.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/protostream_objectwriter.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/protostream_objectwriter.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/proto_writer.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/proto_writer.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/structured_objectwriter.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/type_info.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/type_info.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/type_info_test_helper.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/type_info_test_helper.h
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/utility.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/internal/utility.h
        ${PROTOBUF_ROOT}/google/protobuf/util/json_util.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/message_differencer.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/time_util.cc
        ${PROTOBUF_ROOT}/google/protobuf/util/type_resolver_util.cc
        )
# ${PROTOBUF_ROOT}/google/protobuf/stubs/mathlimits.cc

set(WPNLIBSRC
        ${WPN_ROOT}/wp-storage-file.cpp
        ${WPN_ROOT}/notify2string.cpp
        ${WPN_ROOT}/wp-push.cpp
        ${WPN_ROOT}/wp-subscribe.cpp
        ${WPN_ROOT}/wp-connection.cpp
        ${WPN_ROOT}/sslfactory.cpp
        ${WPN_ROOT}/mcs/mcsclient.cpp
        ${WPN_ROOT}/mcs/heartbeat.cpp
        ${WPN_ROOT}/utilqr.cpp
        ${WPN_ROOT}/utilfile.cpp
        ${WPN_ROOT}/utilstring.cpp
        ${WPN_ROOT}/params.cpp
        ${WPN_ROOT}/commandoutput.cpp
        ${WPN_ROOT}/endpoint.cpp
        ${WPN_ROOT}/vapid.cpp
        ${WPN_ROOT}/utilvapid.cpp
        ${WPN_ROOT}/utilrecv.cpp
        ${WPN_ROOT}/utilinstance.cpp
        ${WPN_ROOT}/wp-registry.cpp
        ${WPN_ROOT}/utiljson.cpp
        ${WPN_ROOT}/config-filename.cpp
        ${WPN_ROOT}/vapid-api.cpp
        ${WPN_ROOT}/errlist.cpp

        ${MCS_GEN_ROOT}/mcs.pb.cc
        ${MCS_GEN_ROOT}/android_checkin.pb.cc
        ${MCS_GEN_ROOT}/checkin.pb.cc

        ${QR_ROOT}/BitBuffer.cpp
        ${QR_ROOT}/QrCode.cpp
        ${QR_ROOT}/QrSegment.cpp

        ${ECEC_ROOT}/base64url.c
        ${ECEC_ROOT}/decrypt.c
        ${ECEC_ROOT}/encrypt.c
        ${ECEC_ROOT}/keys.c
#        ${ECEC_ROOT}/params.c
        ${ECEC_ROOT}/trailer.c
        )

add_definitions(-DHAVE_PTHREAD=1 -DHAVE_ZLIB=1 -DLIB_PBJSON -DUSE_JSON_RAPID)

add_library( # Sets the name of the library.
        wpn-lib
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
        wpn-lib.cpp
        ${WPNLIBSRC}
        ${PROTO_SRC}
        )

# Searches for a specified prebuilt library and stores the path as a variable.
find_library(liblog log)
find_library(libz z)

add_library(libcurl STATIC IMPORTED)
set_target_properties(libcurl PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libcurl.a)
add_library(libssl STATIC IMPORTED)
set_target_properties(libssl PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libssl.a)
add_library(libcrypto STATIC IMPORTED)
set_target_properties(libcrypto PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libcrypto.a)

# Specifies libraries CMake should link to your target library.
target_link_libraries( # Specifies the target library.
        wpn-lib
        ${liblog} ${libz} libcurl libssl libcrypto )
