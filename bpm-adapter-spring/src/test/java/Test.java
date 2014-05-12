import org.activiti.engine.RuntimeService;
import org.bpm.engine.runtime.BpmRuntime;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by serv on 14-5-8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext*.xml"})
public class Test {

    @Autowired
    BpmRuntime bpmRuntime;
    @Autowired
    RuntimeService runtimeService;

    @org.junit.Test
    public void test02(){

        bpmRuntime.claimTask(null,null);
    }
}