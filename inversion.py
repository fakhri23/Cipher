def count(arr): 
    n = len(arr)
    if (n ==1 ): 
        return (arr,0)
    else: 
        m = int(n/2)
        (a,x) = count(arr[:m])
        (b,y) = count(arr[m:])
        (c,z) = sort_and_count(a,b)
        return (c,x+y+z)

def sort_and_count(a,b): 
    result = list()
    na = len(a)
    nb = len(b)
    total = na+nb 
    Ninv = 0 
    i = 0 
    j = 0 
    while(len(result) < total) : 
        
        if (a[i] <= b[j]): 
            result.append(a[i])
            i+=1 
        else: 
            result.append(b[j])
            j+=1
            Ninv+=na-i
        print("Niv", Ninv)
        if (i  >= na): 
            result+=b[j:]
        elif ( j >=nb): 
            while(i<na): 
                result.append(a[i])
                i+=1 
                Ninv+=na-i 
        
        
        
    return (result, Ninv)

if __name__=="__main__": 
    print(count([1, 5, 4, 2, 3]))
