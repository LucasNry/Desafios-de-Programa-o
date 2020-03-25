# Daniel Macris - 11271035
# Lucas D A Nery - 11315040 

def get_frequency(line): # O(n)
    frequency_dict = {}
    for char in line: # n
        if not frequency_dict.get(char):
            frequency_dict[char] = 1
        else:
            frequency_dict[char] += 1

    return frequency_dict

def final_sort(tuple_array): # O(n)
    for i in range(0,len(tuple_array) - 1): # n
        if (tuple_array[i][1] == tuple_array[i + 1][1]):
            if(ord(tuple_array[i][0]) < ord(tuple_array[i + 1][0])):
                intermediate = tuple_array[i]
                tuple_array[i] = tuple_array[i + 1]
                tuple_array[i + 1] = intermediate
    return tuple_array

def print_results(freq_dict): # O(n)
    tuple_array = [(key, freq_dict[key]) for key in freq_dict.keys()] # n
    sorted_tuples = sorted(tuple_array, key = lambda item: item[1]) # n
    final_sorted_array = final_sort(sorted_tuples) # n
    for key in final_sorted_array: # n
        print(ord(key[0]), key[1])
                    
while True:
  try:
    line = input()
    freq_dict = get_frequency(line)
    print_results(freq_dict)
  except EOFError:
    break