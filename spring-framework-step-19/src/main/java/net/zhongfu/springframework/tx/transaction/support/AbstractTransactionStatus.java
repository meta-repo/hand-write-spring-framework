package net.zhongfu.springframework.tx.transaction.support;

import net.zhongfu.springframework.tx.transaction.NestedTransactionNotSupportedException;
import net.zhongfu.springframework.tx.transaction.SavepointManager;
import net.zhongfu.springframework.tx.transaction.TransactionException;
import net.zhongfu.springframework.tx.transaction.TransactionStatus;

import java.io.IOException;

/**
 * @author 小傅哥，微信：fustack
 * @description Abstract base implementation of the TransactionStatus interface.
 * @date 2022/3/16
 * @github https://github.com/fuzhengwei/CodeDesignTutorials
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public abstract class AbstractTransactionStatus implements TransactionStatus {

    private boolean rollbackOnly = false;

    private boolean completed = false;

    private Object savepoint;

    @Override
    public void setRollbackOnly() {
        this.rollbackOnly = true;
    }

    @Override
    public boolean isRollbackOnly() {
        return (isLocalRollbackOnly() || isGlobalRollbackOnly());
    }

    public boolean isLocalRollbackOnly() {
        return this.rollbackOnly;
    }

    public boolean isGlobalRollbackOnly() {
        return false;
    }

    @Override
    public void flush() throws IOException {

    }

    public void setCompleted() {
        this.completed = true;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    public void setSavepoint(Object savepoint) {
        this.savepoint = savepoint;
    }

    public Object getSavepoint() {
        return savepoint;
    }

    @Override
    public boolean hasSavepoint() {
        return this.savepoint != null;
    }

    @Override
    public Object createSavepoint() throws TransactionException {
        return getSavepointManager().createSavepoint();
    }

    @Override
    public void rollbackToSavepoint(Object savepoint) throws TransactionException {
        getSavepointManager().rollbackToSavepoint(savepoint);
    }

    @Override
    public void releaseSavepoint(Object savepoint) throws TransactionException {
        getSavepointManager().releaseSavepoint(savepoint);
    }

    protected SavepointManager getSavepointManager() {
        throw new NestedTransactionNotSupportedException("This transaction does not support savepoints");
    }

}
