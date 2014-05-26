package org.bpm.engine.runtime;

/**
 * 运行时接口，主要实现流程流转相应功能点。如：提交，退回，终止等操作
 * Created by rocky on 14-5-4.
 */
public interface BpmRuntime extends BpmTaskRuntime , BpmProcessRuntime , BpmAdminRuntime{
}
