package org.mps.boundedqueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ArrayBoundedQueueTest{
    private ArrayBoundedQueue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new ArrayBoundedQueue<>(4);
    }

    @Nested
    @DisplayName("When a new queue is created")
    public class creatingQueue{
        @Test
        @DisplayName("Assert that queue size is 0")
        public void test_NewQueueSizeZero(){
            ArrayBoundedQueue<Integer> queueEx = new ArrayBoundedQueue<>(4);

            assertThat(queueEx.size()).isEqualTo(0);
        }
        @Test
        @DisplayName("Queue with size = 0")
        public void test_EmptyQueueSizeZero_throwsIllegalArgumentException(){
            int capacity = 0;
        
            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new ArrayBoundedQueue<>(capacity))
                    .withMessage("ArrayBoundedException: capacity must be positive");               
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
                .containsExactly(2, 3);
        }

        @Test
        @DisplayName("Putting an element on a full list")
        public void test_PuttingAnElementOnFullList_throwsFullBoundedQueueException(){
            int values;

            for(values = 0; values < 4; values++){
                queue.put(values);
            }

            assertThatExceptionOfType(FullBoundedQueueException.class)
                .isThrownBy(() -> queue.put(5))
                    .withMessage("put: full bounded queue");
        }
        @Test
        @DisplayName("Putting a null element")
        public void test_PuttingANullElement_throwsIllegalArgumentException(){
            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> queue.put(null))
                    .withMessage("put: element cannot be null");
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
            int val = 1;

            queue.put(val);

            assertThat(queue).isNotEmpty()
                .containsExactly(1)
                .hasSize(1);
        }

        @Test
        @DisplayName("Test getFirst cuando el primer elemento del buffer esta en la posicion 2")
        public void testGetFirst_WithFirstElementInPosition1_Return1(){
            int val = 1;
            int val2 = 2;

            queue.put(val);
            queue.put(val2);
            queue.get();

            assertThat(queue).isNotEmpty()
                .containsExactly(2)
                .hasSize(1);   
            assertThat(queue.getFirst()).isEqualTo(1);         
        }

        @Test
        @DisplayName("Test getLast cuando el ultimo elemento del buffer esta en la posicion 0")
        public void testGetFirst_WithLastElementInPosition0_Return0(){
            int values;

            for(values = 0; values < 4; values++){
                queue.put(values);
            }
            queue.get();

            assertThat(queue).isNotEmpty()
                .containsExactly(1, 2, 3)
                .hasSize(3);
            assertThat(queue.getLast()).isEqualTo(0);
        }

    }

    @Nested
    @DisplayName("Testing sizes of the queue")
    public class sizeQueue{

        @Test
        @DisplayName("Test isFull cuando la cola tiene un elemento")
        public void testAdvance_Size4With1Element_ReturnFalse(){
            int val = 1;

            queue.put(val);;

            assertThat(queue.isFull()).isFalse();
            assertThat(queue).hasSize(1)
                .containsExactly(1)
                .hasSize(1)
                .doesNotContainNull();
        }

        @Test
        @DisplayName("Test isFull cuando la cola es de tamaño 4 y tiene 4 elementos")
        public void testAdvance_Size4With4Element_ReturnTrue(){
            int values;

            for(values = 0; values < 4; values++){
                queue.put(values);
            }

            assertThat(queue.isFull()).isTrue();
            assertThat(queue).hasSize(4)
                .containsExactly(0, 1, 2, 3)
                .hasSize(4)
                .doesNotContainNull();
        }

        @Test
        @DisplayName("Test isEmpty cuando la cola tiene un elemento")
        public void testIsEmpty_Size4With1Element_ReturnFalse(){
            int val = 1;

            queue.put(val);

            
            assertThat(queue).hasSize(1)
                .containsExactly(1)
                .hasSize(1)
                .doesNotContainNull()
                .isNotEmpty();
        }

        @Test
        @DisplayName("Test isEmpty cuando la cola esta vacia")
        public void testAdvance_Size4WithNoElement_ReturnFalse(){
            int val =  1;

            queue.put(val);
            queue.get();

            assertThat(queue).hasSize(0)
                .isEmpty();
        }

        @Test
        @DisplayName("Test size cuando la cola esta recien inicializada")
        public void testSize_WithNoElement_Return0(){
            int val = 1;

            queue.put(val);
            queue.get();

            assertThat(queue).hasSize(0)
                .isEmpty();
        }

        @Test
        @DisplayName("Test size cuando la cola tiene un elemento")
        public void testSize_With1Element_Return1(){
            int val = 1;

            queue.put(val);

            assertThat(queue).hasSize(1)
                .containsExactly(1)
                .hasSize(1)
                .doesNotContainNull();
        }
    }
}