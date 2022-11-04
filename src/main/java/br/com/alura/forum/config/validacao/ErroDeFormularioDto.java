package br.com.alura.forum.config.validacao;

//Classe que representa um erro de validação
public class ErroDeFormularioDto {

    private String campoDoErro;
    private String mensagemDeErro;

    public ErroDeFormularioDto(String campoDoErro, String mensagemDeErro) {
        this.campoDoErro = campoDoErro;
        this.mensagemDeErro = mensagemDeErro;
    }

    public String getCampoDoErro() {
        return campoDoErro;
    }

    public String getMensagemDeErro() {
        return mensagemDeErro;
    }

}
