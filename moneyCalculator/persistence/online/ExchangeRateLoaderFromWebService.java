package moneyCalculator.persistence.online;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import moneyCalculator.model.Currency;
import moneyCalculator.model.ExchangeRate;

public class ExchangeRateLoaderFromWebService implements ExchangeRateLoader {

    private final String web = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/";

    @Override
    public ExchangeRate exchangeRateLoader(Currency from, Currency to) {
        return new ExchangeRate(from, to, read(from.getCode(), to.getCode()));
    }

    private double read(String codeFrom, String codeTo) {

        try {
            return Double.parseDouble(
                    getStringRateFromJSONLine(read(new URL(this.web + codeFrom + "/" + codeTo + ".json"))));
        } catch (NumberFormatException | IOException e) {
            return 0;
        }
    }

    private String read(URL url) throws IOException {
        InputStream inputStream = url.openStream();
        byte[] buffer = new byte[1024];
        int length = inputStream.read(buffer);

        return new String(buffer, 1, length);
    }

    private String getStringRateFromJSONLine(String line) {
        String[] split = line.split(",");
        return split[1].substring(split[1].indexOf(":") + 1, split[1].indexOf("}") - 1);
    }

}