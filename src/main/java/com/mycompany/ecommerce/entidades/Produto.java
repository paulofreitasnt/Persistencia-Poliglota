package com.mycompany.ecommerce.entidades;

import com.mycompany.ecommerce.interfaces.MongoDBObject;
import org.bson.Document;

public class Produto implements MongoDBObject<Produto>{

    private int id;
    private String descricao;
    private double preco;

    public Produto() {
    }

    public Produto(int id, String descricao, double preco) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", descricao=" + descricao + ", preco=" + preco + '}';
    }

    @Override
    public Document toDocument() {
        Document doc = new Document();
        doc.append("_id", id);
        doc.append("descricao", descricao);
        doc.append("preco", preco);
        return doc;
    }

    @Override
    public Produto fromDocument(Document doc) {
        id = doc.getInteger("_id");
        descricao = doc.getString("descricao");
        preco = doc.getDouble("preco");
        return this;
    }
    
}
