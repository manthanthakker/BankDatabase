// RUN THESE COMMANDS IN THE SAME ORDER SCRIPT FOR CREATING A DATABASE IN MYSQL.



create database bank;
use bank;

create table Account(
	id int primary key auto_increment,
	Account_number double not null unique,
	balance double not null,
	DateOfStart date not null,
	DateOfEnd date not null,
	status enum("Activated", "Deactivated"
));


create table SavingsAccount(
	sid int primary key,
	InterestRate double not null,
	foreign key(sid) references Account(id)
	on update cascade
	on delete cascade
	);


create table CheckingAccount(
	caid int primary key,
	foreign key(caid) references Account(id)
	on update cascade
	on delete cascade
);


create table Person(
	id int primary key auto_increment,
	fname varchar(200) not null,
	lname varchar(200) not null,
	Dob Date,
	Address varchar(200) not null,
	contact_no double,
	password varchar(200) not null,
	emailId varchar(200)
);

create table Customer(
	cust_id int primary key,
	foreign key(cust_id) references person(id)
	on update cascade
	on delete cascade
);
 



create table Personaccount(
	accid int not null,
	personid int not null,
	foreign key(accid) references Account(id)
	on update cascade
	on delete cascade,
	foreign key(personid) references Person(id)
	on update cascade
	on delete cascade,
    primary key(accid,personid)

);


create table card(
	card_id int primary key auto_increment,
	card_no double not null,
	issueDate date not null,
	expDate date not null,
	cvv int not null,
	status enum("Activated", "Deactivated"),
	usedby int,
	foreign key(usedby) references Person(id)
	on update cascade
	on delete cascade
);

create table creditcard(
	credit_id int primary key,
	MaxCredit double not null,
	foreign key(credit_id) references card(card_id)
	on update cascade
	on delete cascade
);

create table debitcard(
	debit_id int primary key,
	foreign key(debit_id) references card(card_id)
	on update cascade
	on delete cascade
);

create table Transaction(
	tid varchar(100) not null unique,
	toAccount double not null,
	fromAccount double not null,
	amount double not null,
	type enum ('deposit','withdraw','transfer','pay'),
	updatedBy int not null,
	updates int not null,
	foreign key(updatedBy) references person(id)
	on update cascade
	on delete cascade,
	foreign key(updates) references Account(id)
	on update cascade
	on delete cascade,
	primary key(updatedBy,updates)
);


create table Loan(
	loanid int primary key auto_increment,
	amount double not null,
	amountpaid double,
	amountleft double,
	cust_id int not null,
	status varchar(200),
	foreign key(cust_id) references customer(cust_id)
	on update cascade
	on delete no action
);


create table EducationLoan(
	eid int primary key,
	roi double not null,
	university varchar(200) not null,
	foreign key(eid) references Loan(loanid)
	on update cascade
	on delete cascade
);

create table HomeLoan(
	hid int primary key,
	roi double not null,
	address varchar(200) not null,
	foreign key(hid) references Loan(loanid)
	on update cascade
	on delete cascade
);

create table CarLoan(
	cid int primary key,
	roi double not null,
	carModel varchar(200) not null,
	foreign key(cid) references Loan(loanid)
	on update cascade
	on delete cascade
);



TEST DATA:


select loanid,amount,amountpaid,amountleft,roi,university from 
EducationLoan e 
Left Join Loan l on
l.loanid=e.eid
and l.cust_id=7;

// 3 customers : Manthan, Vineet, Shail
insert into person values (1,"Manthan","Thakker",CURRDATE(),"235 ParkDrive",123456789,"6994","thakker.m@husky.neu.edu");
insert into person values (2,"Vineet","Trivedi",CURRDATE(),"235 ParkDrive",312312,"4568","trivedi.g@husky.neu.edu");
insert into person values (3,"Shail","Shah",CURRDATE(),"235 ParkDrive",1231231231,"12312","shail.s@husky.neu.edu");

// SavingsAccount

// Three accounts with acc id as 1,2,3
// With Account c=number 11, 22,33
// with each account of Manthan. Vineet, Shail respectively!
insert into  account values (1,11,200,CURDATE(),DATE_ADD(CURDATE(),INTERVAL 30 DAY),"ACTIVATED");
insert into  account values (2,22,300,CURDATE(),DATE_ADD(CURDATE(),INTERVAL 40 DAY),"ACTIVATED");

insert into  account values (3,33,400,CURDATE(),DATE_ADD(CURDATE(),INTERVAL 50 DAY),"ACTIVATED");
insert into savingsaccount values (1,8.5);
insert into savingsaccount values (2,8.5);

insert into savingsaccount values (3,8.5);
insert into personaccount values(1,1);
insert into personaccount values (2,2);
insert into personaccount values(3,3);


//checking ACCOUNT for manthan acc_id:4 and acc_no : 44
insert into  account values (4,44,400,CURDATE(),DATE_ADD(CURDATE(),INTERVAL 50 DAY),"ACTIVATED");

insert into checkingaccount values(4);

insert into personaccount values(4,1);


// Manthan has credit card with credit_id 1 and and credit_no 

insert into card values(1,54321,CURDATE(),DATE_ADD(CURDATE(),INTERVAL 50 DAY),231,"Activated",1);
 

insert into creditcard values (1,1000);




