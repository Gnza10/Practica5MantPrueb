package org.mps.boundedqueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyChar;

public class ArrayBoundedQueueTest{
    private ArrayBoundedQueue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new ArrayBoundedQueue<>(4);
    }

    @Test
    @DisplayName("Test get con una lista vacia")
    public void testGet_EmptyQueue_ReturnException(){
        assertThatExceptionOfType(EmptyBoundedQueueException.class)
            .isThrownBy(() -> { queue.get();})
                .withFailMessage("get: empty bounded queue");
                
    }

    @Test
    @DisplayName("Test get con una lista de un elemento")
    public void testGet_NotEmpty_ReturnItem(){
        queue.put(1);
        int expectedvalue = 1;

        int actualValue = queue.get();

        assertThat(actualValue).isEqualTo(expectedvalue);
    }

    @Test
    @DisplayName("Test isFull cuando la cola tiene un elemento")
    public void testAdvance_Size4With1Element_ReturnFalse(){
        queue.put(1);

        boolean actualValue = queue.isFull();

        assertThat(actualValue).isFalse();
    }

    @Test
    @DisplayName("Test isFull cuando la cola es de tamaño 4 y tiene 4 elementos")
    public void testAdvance_Size4With4Element_ReturnTrue(){
        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.put(4);

        boolean actualValue = queue.isFull();

        assertThat(actualValue).isTrue();
    }

    @Test
    @DisplayName("Test isEmpty cuando la cola tiene un elemento")
    public void testIsEmpty_Size4With1Element_ReturnFalse(){
        queue.put(1);

        boolean actualValue = queue.isEmpty();

        assertThat(actualValue).isFalse();
    }

    @Test
    @DisplayName("Test isEmpty cuando la cola esta vacia")
    public void testAdvance_Size4WithNoElement_ReturnFalse(){

        boolean actualValue = queue.isEmpty();

        assertThat(actualValue).isTrue();
    }

    @Test
    @DisplayName("Test size cuando la cola esta recien inicializada")
    public void testSize_WithNoElement_Return0(){
        int expectedvalue = 0;

        int actualValue = queue.size();

        assertThat(actualValue).isEqualTo(expectedvalue);
    }

    @Test
    @DisplayName("Test size cuando la cola tiene un elemento")
    public void testSize_With1Element_Return1(){
        queue.put(1);
        int expectedvalue = 1;

        int actualValue = queue.size();

        assertThat(actualValue).isEqualTo(expectedvalue);
    }

    @Test
    @DisplayName("Test getFirst cuando el primer elemento del buffer esta en la posicion 2")
    public void testGetFirst_WithFirstElementInPosition1_Return1(){
        queue.put(1);
        queue.put(2);
        queue.get();
        int expectedvalue = 1;

        int actualValue = queue.getFirst();

        assertThat(actualValue).isEqualTo(expectedvalue);
    }

    @Test
    @DisplayName("Test getLast cuando el ultimo elemento del buffer esta en la posicion 0")
    public void testGetFirst_WithLastElementInPosition0_Return0(){
        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.put(4);
        queue.get();
        int expectedvalue = 0;

        int actualValue = queue.getLast();

        assertThat(actualValue).isEqualTo(expectedvalue);
    }

}