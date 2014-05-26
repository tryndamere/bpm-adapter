package org.bpm.engine;

import org.bpm.engine.definition.BpmDefinition;
import org.bpm.engine.runtime.BpmRuntime;

/**
 * 服务工厂
 * Created by rocky on 14-5-4.
 */
public interface BpmEngine {

    public BpmRuntime getBpmRuntime();

    public BpmDefinition getBpmDefinition();

    public void close();

}
