package org.mps.boundedqueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
<<<<<<< HEAD
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyChar;
=======
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import net.bytebuddy.asm.Advice.OffsetMapping.Factory.Illegal;

import static org.assertj.core.api.Assertions.*; 
>>>>>>> main

public class ArrayBoundedQueueTest{
    private ArrayBoundedQueue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new ArrayBoundedQueue<>(4);
    }

<<<<<<< HEAD
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
=======
    @Nested
    @DisplayName("When a new queue is created")
    public class creatingQueue{

        @Test
        @DisplayName("Queue with size > 0")
        public void test_EmptyQueue_size0(){
            queue = new ArrayBoundedQueue<>(4);
        
            assertThat(queue).hasSize(0);
        }

        @Test
        @DisplayName("Queue with size = 0")
        public void test_EmptyQueueSizeZero_throwsIllegalArgumentException(){
            int capacity = 0;
        
            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new ArrayBoundedQueue<>(capacity));               
        }
    }

    @Nested
    @DisplayName("putting and getting elements")
    public class putGetElements{
        
        @Test
        @DisplayName("Putting an element")
        public void test_PuttingAnElementWithEnoughSpace(){
            int value = 2;
            int value2 = 3;
            
            queue.put(value);
            queue.put(value2);

            assertThat(queue).isNotEmpty()
                .containsExactly(2, 3)
                .hasSize(2);
        }

        @Test
        @DisplayName("Putting an element on a full list")
        public void test_PuttingAnElementOnFullList_throwsFullBoundedQueueException(){
            int values;

            for(values = 0; values < 4; values++){
                queue.put(values);
            }

            assertThatExceptionOfType(FullBoundedQueueException.class).isThrownBy(() -> queue.put(5));
        }
>>>>>>> main
    }

}