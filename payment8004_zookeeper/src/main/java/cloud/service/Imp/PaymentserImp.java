package cloud.service.Imp;


import cloud.dao.PaymentDao;
import cloud.service.PaymentService;

import com.cloud.entities.Payment;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentserImp implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getById(Long id) {
        return paymentDao.getById(id);
    }
}
