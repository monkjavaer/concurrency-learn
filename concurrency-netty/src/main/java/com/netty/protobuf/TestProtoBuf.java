package com.netty.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author monkjavaer
 * @date 2019/7/19 16:14
 */
public class TestProtoBuf {
    public static void main(String[] args) {

        try {
            UserInfoProto.UserInfo userinfo = getUserInfo("proto");
            System.out.println(userinfo.toString());
            System.out.println(UserInfoProto.UserInfo.parseFrom(userinfo.toByteArray()));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    public static UserInfoProto.UserInfo getUserInfo(String name){
       return UserInfoProto.UserInfo.newBuilder()
                .setName(name)
                .setAge(18)
                .addAddress(
                        UserInfoProto.Address.newBuilder()
                                .setAddressname("beijing 001")
                                .setAdressno(911))
                .build();
    }
}
