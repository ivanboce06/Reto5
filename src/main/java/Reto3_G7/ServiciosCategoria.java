/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G7;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Grupo 7
 */
@Service
public class ServiciosCategoria {
     @Autowired
    private RepositorioCategoria metodosCrud;

    public List<Categoria> getAll() {
        return metodosCrud.getAll();
    }

    public Optional<Categoria> getCategoria(int CategoriaId) {
        return metodosCrud.getCategoria(CategoriaId);
    }

    public Categoria save(Categoria categoria) {
        if (categoria.getId()== null) {
            return metodosCrud.save(categoria);
        } else {
            Optional<Categoria> categoria1 = metodosCrud.getCategoria(categoria.getId());
            if (categoria1.isEmpty()) {
                return metodosCrud.save(categoria);
            } else {
                return categoria;
            }
        }
    }

    public Categoria update(Categoria categoria){
          if (categoria.getId()!=null){
            Optional<Categoria> e = metodosCrud.getCategoria(categoria.getId());
            if(!e.isEmpty()){
                if(categoria.getName()!=null){
                    e.get().setName((categoria.getName()));
                }
                if(categoria.getDescription()!=null){
                    e.get().setDescription(categoria.getDescription());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return categoria;
            }
        }else{
            return categoria;
        }
    }
    public boolean deletecategoria(int categoriaId){
        Boolean d=getCategoria(categoriaId).map(categoria -> {
            metodosCrud.delete(categoria);
            return true;
        }).orElse(false);
        return d;
    }
    
}
