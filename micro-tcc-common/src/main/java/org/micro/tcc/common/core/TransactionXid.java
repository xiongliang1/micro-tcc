package org.micro.tcc.common.core;


import org.micro.tcc.common.util.SnowFlake;

import javax.transaction.xa.Xid;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;

/**
*@author jeff.liu
*@desc   全局id
*@date 2019/8/27
*/
public class TransactionXid implements Serializable {


    private static final long serialVersionUID = 5549076002296308508L;

    private String globalTccTransactionId;


    /**
     * 生成全局唯一id
     */
    public TransactionXid() {
        String groupId= SnowFlake.get().nextGroupId();
        globalTccTransactionId=groupId;

    }
    public String getGlobalTccTransactionId() {
        return globalTccTransactionId;
    }

    public void setGlobalTccTransactionId(String globalTccTransactionId) {
        this.globalTccTransactionId = globalTccTransactionId;
    }


    public TransactionXid(Object uniqueIdentity) {
        UUID branchUuid = null;
        if (uniqueIdentity instanceof UUID) {
            branchUuid = (UUID) uniqueIdentity;
        } else {
            try {
                branchUuid = UUID.fromString(uniqueIdentity.toString());
            } catch (IllegalArgumentException e) {
                byte[] bytes = uniqueIdentity.toString().getBytes();
                if (bytes.length > 16) {
                    throw new IllegalArgumentException("UniqueIdentify is illegal, the value is :" + uniqueIdentity.toString());
                }
                branchUuid = UUID.nameUUIDFromBytes(bytes);
            }
        }


    }


    public TransactionXid(String globalTransactionId) {
        this.globalTccTransactionId = globalTransactionId;

    }
    public TransactionXid(String globalTransactionId, byte[] branchQualifier) {
        this.globalTccTransactionId = globalTransactionId;

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(globalTccTransactionId);
        return stringBuilder.toString();
    }

}


