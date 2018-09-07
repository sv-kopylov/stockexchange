package ru.kopylov.stockexshange.bl;

import ru.kopylov.stockexshange.DAO.OrderDAO;
import ru.kopylov.stockexshange.Exceptions.CriticalException;
import ru.kopylov.stockexshange.Exceptions.NoSuchDefinitionException;
import ru.kopylov.stockexshange.ioc.Context;
import ru.kopylov.stockexshange.model.Order;

/**
 * Created by se on 07.09.2018.
 */
public class MatcherImpl implements Matcher {
    OrderPool saleOrderPool = new OrderPoolImpl();
    OrderPool buyOrderPool = new OrderPoolImpl();
    
    OrderDAO orderDAO;
    OrderExecutor orderExecutor;
    public void init(){
        Context ctx = Context.getInstance();
        try {
            orderDAO = (OrderDAO) ctx.lookup(OrderDAO.class);
            orderExecutor = (OrderExecutorImpl) ctx.lookup(OrderExecutorImpl.class);

        } catch (NoSuchDefinitionException e) {
            throw new CriticalException("Context not initialized "+e.getMessage());
        }
    }
    
    @Override
    public void processOrders() {
        Order incomingOrder;
        Order findOrder;
        while ((incomingOrder=orderDAO.poll())!=null){
            switch (incomingOrder.getType()){
                case SALE:
                    findOrder = buyOrderPool.pollMatching(incomingOrder);
                    if(findOrder!=null){
                        orderExecutor.execute(incomingOrder, findOrder);
                    } else {
                        saleOrderPool.add(incomingOrder);
                    }
                    break;
                case BUY:
                    findOrder = saleOrderPool.pollMatching(incomingOrder);
                    if(findOrder!=null){
                        orderExecutor.execute(incomingOrder, findOrder);
                    } else {
                        buyOrderPool.add(incomingOrder);
                    }
                    break;
            }
        }
    }
}
