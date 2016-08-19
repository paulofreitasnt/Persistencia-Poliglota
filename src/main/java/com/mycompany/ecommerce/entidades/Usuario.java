package com.mycompany.ecommerce.entidades;

import com.mycompany.ecommerce.interfaces.MongoDBObject;
import org.bson.Document;

public class Usuario implements MongoDBObject<Usuario>{

    private int id;
    private String nome;

    public Usuario() {
    }

    public Usuario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + '}';
    }

    @Override
    public Document toDocument() {
        Document doc = new Document();
        doc.append("_id", id);
        doc.append("nome", nome);
        return doc;
    }

    @Override
    public Usuario fromDocument(Document doc) {
        id = doc.getInteger("_id");
        nome = doc.getString("nome");
        return this;
    }

}
