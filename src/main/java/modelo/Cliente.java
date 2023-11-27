
package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String endereco;
    private String cidade;

    public Integer getCodigo() {
        return id;
    }

    public void setCodigo(Integer codigo) {
        this.id = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereço(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
         return "Cliente - " + "Codigo: " + id + " Nome: " + nome + " Endereço: " + endereco + " Cidade: " + cidade;
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

