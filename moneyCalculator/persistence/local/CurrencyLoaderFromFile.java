package moneyCalculator.persistence.local;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import moneyCalculator.model.Currency;
import moneyCalculator.persistence.IteratorReader;

public class CurrencyLoaderFromFile implements CurrencyLoader {

    private final String filename;

    public CurrencyLoaderFromFile(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Currency> loadCurrencies() {
        List<Currency> list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(this.filename)));

            IteratorReader iteratorReader = new IteratorReader(reader);

            for (String line : iteratorReader) {
                list.add(currencyOf(line));
            }

        } catch (FileNotFoundException exc) {
            System.out.println(exc.getMessage());
        }

        return list;
    }

    private Currency currencyOf(String line) {
        String[] split = line.split(",");
        return new Currency(split[0], split[1], split[2]);
    }
}
