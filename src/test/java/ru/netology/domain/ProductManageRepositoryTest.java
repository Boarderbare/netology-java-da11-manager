package ru.netology.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductManageRepositoryTest {
    private ProductManager manager = new ProductManager();
    private Repository repository = new Repository();
    private Product first = new Product(1, "Motocycle", 9000);
    private Product second = new Product(2, "Photocamera", 1200);
    private Product third = new Book(3, "War and Peace", 34, "Leo Tolstoy");
    private Product fourth = new Smartphone(4, "iPhone", 1500, "Apple");

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        manager.addItem(first);
        manager.addItem(second);
        manager.addItem(third);
        manager.addItem(fourth);
    }

    @Test
    public void shouldFinfAll() {
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first, second, third, fourth};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldRemoveById() {
        repository.removeById(2);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first, third, fourth};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchBook() {

        Product[] actual = manager.searchBy("Peace");
        Product[] expected = new Product[]{third};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchSmartphone() {

        Product[] actual = manager.searchBy("iPhone");
        Product[] expected = new Product[]{fourth};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchTwo() {

        Product[] actual = manager.searchBy("Pho");
        Product[] expected = new Product[]{second, fourth};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchAll() {
        Product[] actual = manager.searchBy("e");
        Product[] expected = new Product[]{first, second, third, fourth};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSearchNothing() {
        Product[] actual = manager.searchBy("hrr");
        Product[] expected = new Product[0];
        assertArrayEquals(actual, expected);
    }
}