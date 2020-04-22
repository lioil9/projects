```
lioil@lioildeMac-mini packagetest2 % tree club 
club
└── banyuan
    ├── animal
    │   └── Elephant.java
    ├── machine
    │   └── Fridge.java
    └── main
        └── Main.java

4 directories, 3 files
lioil@lioildeMac-mini packagetest2 % javac -d out club/banyuan/animal/*.java club/banyuan/machine/*.java club/banyuan/main/*.java
lioil@lioildeMac-mini packagetest2 % tree out
out
└── club
    └── banyuan
        ├── animal
        │   └── Elephant.class
        ├── machine
        │   └── Fridge.class
        └── mian
            └── Main.class

5 directories, 3 files
```