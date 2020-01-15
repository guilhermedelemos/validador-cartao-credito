package example;

public class LuhnModulus10 implements IdentificationNumberValidator {

    @Override
    public boolean isValid(String numeroCartao) {
        if(numeroCartao == null || numeroCartao.isEmpty()) {
            return false;
        }
        int qtdeDigitos = numeroCartao.length();

        int soma = 0;
        boolean segundo = false;
        for (int i = qtdeDigitos - 1; i >= 0; i--) {
            int digito = numeroCartao.charAt(i) - '0';
            if (segundo) {
                digito = digito * 2;
            }

            soma += digito / 10;
            soma += digito % 10;

            segundo = !segundo;
        }
        return (soma % 10 == 0);
    }

}
