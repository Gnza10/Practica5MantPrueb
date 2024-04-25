package org.mps.boundedqueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import net.bytebuddy.asm.Advice.OffsetMapping.Factory.Illegal;

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
    }

}