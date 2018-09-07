package ru.kopylov.stockexshange.app;

import org.apache.log4j.Logger;
import ru.kopylov.stockexshange.bl.Matcher;
import ru.kopylov.stockexshange.bl.MatcherImpl;
import ru.kopylov.stockexshange.load.ApplicationDataLoader;
import ru.kopylov.stockexshange.load.Saver;

/**
 * Created by se on 07.09.2018.
 */
public class StockExchangeApplication {
    private static final Logger logger = Logger.getLogger(StockExchangeApplication.class);
    private final String clientFileName;
    private final String ordersFileName;
    private final String resultFileName;

    public StockExchangeApplication(String clientFileName, String ordersFileName, String resultFileName) {
        this.clientFileName = clientFileName;
        this.ordersFileName = ordersFileName;
        this.resultFileName = resultFileName;
    }
    public void launch(){
        ApplicationDataLoader applicationDataLoader = new ApplicationDataLoader(clientFileName, ordersFileName);
        applicationDataLoader.load();

        Matcher matcher = new MatcherImpl();
        matcher.init();
        matcher.processOrders();

        Saver saver = new Saver(resultFileName);
        saver.init();
        saver.save();
    }
    public static void main(String[] args) {
        if (args.length < 3) {
            logger.warn("For application running  must be 3 arguments: 1 clients file path, orders filepath and result filepath");
            System.exit(0);
        }
        new StockExchangeApplication(args[0], args[1], args[2]).launch();

    }


}
