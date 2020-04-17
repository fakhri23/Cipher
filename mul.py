import math
def multiply(A,B): 
    
    strA = str(A)
    strB = str(B) 
    n = len(strA)
    m = len(strB)
    print(len(strA)==len(strB))
    if(A<10 or B<10): 
        return A*B
    else: 
        print("A",A)
        print("B",B)
        k = int(n/2)
        t = int(m/2)
        a = int(strA[:k]) 
        print("a",a)
        c = int(strB[:t])
        print("c",c)
        b  = int(strA[k:])
        print("b",b)
        d = int(strB[t:]) 
        print("d",d)
        ac = multiply(a,c)
        ad = multiply(a,d)
        cb = multiply(c,b)
        db = multiply(b,d)
        res = (ac*10**(k+t)+10**k*ad+10**t*cb+db)
        
        return res

        
if __name__=="__main__": 
    x = 3141592653589793314159265358909331415926535897933141592653589793
    y = 2718281828459045271828182845904527182818284590452718281828459045
    z = 3141592653589793238462643383279502884197169399375105820974944592
    t = 2718281828459045235360287471352662497757247093699959574966967627
    print(multiply(z,t))
    print(z*t)
    print(math.pi*math.e)