
Smarthost challenge :
- runs a server on port 8080 that will respond to URI beginning with /book followed by 2 parameters, nbPremiumRooms and nbEconomyRooms
- response is in JSON format. Returns the number of booked premium and economy rooms as well as the profit made.

Ex : http://localhost:8080/book?nbPremiumRooms=2&nbEconomyRooms=7
will return
{"nbBookedPremium":2,"nbBookedEconomy":4,"totalProfitPremium":583,"totalProfitEconomy":189}

install and run: 
- The easiest way to run the server is to import it in an IDE and execute the class BookingApplication as a Java application (this is a SpringBoot Application)

Otherwise, please refer to this page
https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html

Technologies used : 
- Spring boot
- Spring MVC
- Maven

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!!!!!!!!! Notes !!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
My goal while writing this code was not to write the fewest lines that would run the fastest.
Instead, I tried to write clean, readable and reusable code. 
Each method is short, does only one thing and is easy to reuse, should the application be extended.

I did TDD for this exercise. I'm aware I tested only the basic cases, but it was just to provide a glimpse
at how I normally code. I could also have used Mocks for real Unit testing...

Data : 
The name of the datafile is hardcoded. This is bad, but so is using a textfile as database. 
I considered using an in-memory db (H2), but I thought it would go beyond the scope of this
exercise, which was to assert how I write and organize code.

Packaging: 
I used Springboot, which I find amazing for writing an application from scratch and for passing it to 
someone else (like in this case). You don't need anything besides Maven and an IDE, Springboot does the rest,
since it embeds a server (A tomcat here).


Hope you'll like it.

