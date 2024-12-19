## Structura Proiectului

Proiectul are următoarele pachete:

- actions: Clase pentru diverse acțiuni.
- accounts: Clase legate starns de conturi, precum Account/Card, implementarile lor si fabricile folosite.
- objects: Toate clasele.

## Design Patterns

- Factory: Folosit pentru a crea obiecte de tip Account/Card(facut putin pe genunchi dar promit ca modific in partea2).
- Singleton: Folosit pentru a crea o singura instanta a clasei Bank, Output si Input.
- Strategy: Folosit pentru a alege strategia de plata in functie de tipul cardului.

## Implementarea Efectiva A Proiectului

Implementarea a fost una anovoioasa, dar am izbavit😭.
Am inceput prin a crea clasele de baza, precum Account, Card, Bank, si apoi am continuat cu implementarea 
claselor de tip Account si Card. 
Am folosit Factory Pattern pentru a crea obiecte de tip Account si Card. 
Am folosit Singleton Pattern pentru a crea o singura instanta a clasei Bank, Output si Input, 
resetate la fiecare rulare de teste. 
Am folosit Strategy Pattern pentru a alege strategia de plata in functie de tipul cardului. 
Am vrut sa folosesc si alte design patterns, dar m a luat valul.
In clasa Output am implementat metode de afisare a rezultatelor.
In clasa Input am implementat metode de citire a datelor de intrare.
In clasa Bank am implementat clase si metode de baza(Users, Exchange rates), care pun bazele proiectului.
In workflow am implementat executarea comenzilor, in functie de input, cu ajutorul unu hashmap gasit in ActionMap.
In clasa Account am implementat metode de baza pentru conturi, in functie de tipul acestora.
In clasa Card am implementat metode de baza pentru carduri, de asemenea in functie de tipul acestora.
Pe parcursul implementarii m-am folosit des de gestionarea erorilor prin Try Catch, 
in general pentru a verifica daca datele de intrare sunt valide.