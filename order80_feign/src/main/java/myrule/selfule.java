package myrule;


import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class selfule {

    @Bean
    public IRule myRule(){
        return new RandomRule();  //随机负载均衡
    }
}
