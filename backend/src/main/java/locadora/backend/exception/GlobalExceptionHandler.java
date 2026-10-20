package locadora.backend.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // ---------- 404 ----------
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleNaoEncontrado(
            RecursoNaoEncontradoException ex, HttpServletRequest request) {
        ErroResponse body = new ErroResponse(
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    // ---------- 409 ----------
    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErroResponse> handleRegraNegocio(
            RegraNegocioException ex, HttpServletRequest request) {
        ErroResponse body = new ErroResponse(
                HttpStatus.CONFLICT.value(),
                "Violação de regra de negócio",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    // ---------- 400 — validação de @Valid ----------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidacao(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<ErroResponse.CampoInvalido> campos = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> new ErroResponse.CampoInvalido(f.getField(), f.getDefaultMessage()))
                .toList();

        ErroResponse body = new ErroResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                "Um ou mais campos estão inválidos",
                request.getRequestURI(),
                campos
        );
        return ResponseEntity.badRequest().body(body);
    }

    // ---------- 400 — id inválido na URL (ex: /api/atores/abc) ----------
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroResponse> handleTipoInvalido(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        String msg = "Parâmetro '" + ex.getName() + "' com valor inválido: " + ex.getValue();
        ErroResponse body = new ErroResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Parâmetro inválido",
                msg,
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(body);
    }

    // ---------- 400 — body malformado / JSON inválido ----------
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResponse> handleBodyInvalido(
            HttpMessageNotReadableException ex, HttpServletRequest request) {
        ErroResponse body = new ErroResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Corpo da requisição inválido",
                "Não foi possível ler o JSON enviado",
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(body);
    }

    // ---------- 500 — fallback ----------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleGenerico(
            Exception ex, HttpServletRequest request) {
        ErroResponse body = new ErroResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno",
                "Ocorreu um erro inesperado. Contate o suporte.",
                request.getRequestURI()
        );
        // bom colocar um log aqui
        // log.error("Erro inesperado em {}", request.getRequestURI(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
