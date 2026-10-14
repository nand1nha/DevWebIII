package locadora.backend.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import locadora.backend.model.domain.Item;
import locadora.backend.model.repository.ItemRepository;

@Service 
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> listarTodos() {
        return itemRepository.findAll();
    }

    public Item buscarPorId(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public List<Item> listarPorTitulo(Long tituloId) {
        return itemRepository.findByTituloId(tituloId);
    }

    public Item salvar(Item item) {
        if (item.getId() == null || item.getId() == 0) {
            item.setId(null);
        }
        return itemRepository.save(item);
    }

    public Item atualizar(Long id, Item item) {
        item.setId(id);
        return itemRepository.save(item);
    }

    public void excluir(Long id) {
        itemRepository.deleteById(id);
    }
}
