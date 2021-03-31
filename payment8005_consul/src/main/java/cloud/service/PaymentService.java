package cloud.service;


import com.cloud.entities.Payment;
import org.apache.ibatis.annotations.Param;


public interface PaymentService {
    public int create(Payment payment);

    public Payment getById(@Param("id") Long id);


}
