import numpy as np

def max_pooling(input_matrix, pool_size=2, stride=2):
    """
    Applies max pooling on a 2D input matrix.
    
    Parameters:
    - input_matrix: 2D numpy array, the input image or feature map.
    - pool_size: int, the size of the pooling window.
    - stride: int, the stride of the pooling window.
    
    Returns:
    - A 2D numpy array after applying max pooling.
    """
    input_height, input_width = input_matrix.shape
    output_height = (input_height - pool_size) // stride + 1
    output_width = (input_width - pool_size) // stride + 1
    output_matrix = np.zeros((output_height, output_width))
    
    for i in range(output_height):
        for j in range(output_width):
            row_start = i * stride
            row_end = row_start + pool_size
            col_start = j * stride
            col_end = col_start + pool_size
            output_matrix[i, j] = np.max(input_matrix[row_start:row_end, col_start:col_end])
    
    return output_matrix

# 示例
input_matrix = np.array([[1, 2, 3, 4],
                         [5, 6, 7, 8],
                         [9, 10, 11, 12],
                         [13, 14, 15, 16]])

output_matrix = max_pooling(input_matrix, pool_size=2, stride=2)
print("Max Pooling Result:\n", output_matrix)
