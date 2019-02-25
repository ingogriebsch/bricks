package com.github.ingogriebsch.bricks.dashboard;

import static com.github.ingogriebsch.bricks.dashboard.ServiceApplication.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ServiceApplicationTest {

    @Test
    public void main_should_load_context() {
        main(new String[] { "--server.port=0" });
    }
}
