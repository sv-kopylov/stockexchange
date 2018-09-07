package ru.kopylov.stockexshange.bl;

import ru.kopylov.stockexshange.DAO.Register;
import ru.kopylov.stockexshange.DAO.RegisterImpl;
import ru.kopylov.stockexshange.Exceptions.CriticalException;
import ru.kopylov.stockexshange.Exceptions.NoSuchDefinitionException;
import ru.kopylov.stockexshange.ioc.Context;
import ru.kopylov.stockexshange.model.Customer;
import ru.kopylov.stockexshange.model.Order;
import ru.kopylov.stockexshange.model.RegisterItem;
import ru.kopylov.stockexshange.model.Share;

/**
 * Created by se on 06.09.2018.
 */
public class OrderExecutorImpl implements OrderExecutor {
    Register register;

   public void init()  {
       try {
           register = (RegisterImpl)Context.getInstance().lookup(RegisterImpl.class);
       } catch (NoSuchDefinitionException e) {
           throw new CriticalException("Context not initialized "+e.getMessage());
       }
   }

    @Override
//    could be wraped in transaction
    public void execute(Order o1, Order o2) {
        if(o1==null
                ||o2==null
                ||!o1.getShare().equals(o2.getShare())
                ||o1.getPricePerShare()!=o2.getPricePerShare()
                ||o1.getSharesNum()!=o2.getSharesNum()
                ||o1.getType().equals(o2.getType()))

        {
            throw new IllegalArgumentException("Orders does not match each other, or either is null");
        }
        execute(o1);
        execute(o2);

    }
    private void execute (Order order){
        Customer customer = order.getCustomer();
        Share share = order.getShare();
        RegisterItem registerItem = register.find(customer, share);

        long cashBalance = customer.getCashBalance();
        long itemBalance = registerItem.getItemBalance();

        switch (order.getType()){
            case BUY:
                cashBalance-=order.getPricePerShare()*order.getSharesNum();
                itemBalance+=order.getSharesNum();
                break;
            case SALE:
                cashBalance+=order.getPricePerShare()*order.getSharesNum();
                itemBalance-=order.getSharesNum();
                break;

        }
        customer.setCashBalance(cashBalance);
        registerItem.setItemBalance(itemBalance);
        order.setExecuted();

    }
}
