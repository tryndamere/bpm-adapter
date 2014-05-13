import org.bpm.engine.runtime.BpmRuntime;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by serv on 14-5-8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext*.xml"})
@Transactional
public class Test {

    @Autowired
    BpmRuntime bpmRuntime;

    @org.junit.Test
    public void test02(){

        bpmRuntime.startProcess(null,null,null,null,null);
    }
}
