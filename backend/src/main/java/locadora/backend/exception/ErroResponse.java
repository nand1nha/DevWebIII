package locadora.backend.exception;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErroResponse {
    
    private Instant timestamp;
    private Integer status;
    private String erro;
    private String mensagem;
    private String path;
    private List<CampoInvalido> detalhes;

    public ErroResponse(Integer status, String erro, String mensagem, String path) {
        this.timestamp = Instant.now();
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.path = path;
    }

    public ErroResponse(Integer status, String erro, String mensagem, String path,
                        List<CampoInvalido> detalhes) {
        this(status, erro, mensagem, path);
        this.detalhes = detalhes;
    }

    // getters
    public Instant getTimestamp() { return timestamp; }
    public Integer getStatus() { return status; }
    public String getErro() { return erro; }
    public String getMensagem() { return mensagem; }
    public String getPath() { return path; }
    public List<CampoInvalido> getDetalhes() { return detalhes; }

    public record CampoInvalido(String campo, String mensagem) {}
}
