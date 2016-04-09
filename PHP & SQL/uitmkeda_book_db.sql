-- phpMyAdmin SQL Dump
-- version 4.0.10.7
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Feb 25, 2016 at 03:56 PM
-- Server version: 5.5.47-cll
-- PHP Version: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `uitmkeda_book_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_fname` varchar(45) DEFAULT NULL,
  `admin_lname` varchar(45) DEFAULT NULL,
  `admin_ic` varchar(45) DEFAULT NULL,
  `admin_password` varchar(45) DEFAULT NULL,
  `admin_phone` varchar(45) DEFAULT NULL,
  `admin_email` varchar(45) DEFAULT NULL,
  `admin_status` varchar(45) DEFAULT NULL,
  `university_uni_id` int(11) NOT NULL,
  PRIMARY KEY (`admin_id`,`university_uni_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `admin_fname`, `admin_lname`, `admin_ic`, `admin_password`, `admin_phone`, `admin_email`, `admin_status`, `university_uni_id`) VALUES
(1, 'Dr Abd Latif', 'Abdul Rahman', '900127-02-5429', 'drlatif', '0194034061', 'abdlatif@uitmkedah.net', '1', 1),
(2, 'Ku', 'Azhar', '900127-02-5429', '12345', '0194034061', 'mr.panda1990@gmail.com', '1', 2);

-- --------------------------------------------------------

--
-- Table structure for table `announcement`
--

CREATE TABLE IF NOT EXISTS `announcement` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(500) NOT NULL,
  `CONTENT` longtext NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `announcement`
--

INSERT INTO `announcement` (`ID`, `TITLE`, `CONTENT`) VALUES
(6, 'Newspaper collection Available', 'Starting today newspaper collections are available in NDLS.\r\nGrab this opportunities to become more knowledgeable and informative students by access our NDLS.\r\n'),
(3, 'Welcome to NDLS', 'The NORLISYA Digital Library is an academic library that serves the students, lecturers and the staff in NORLISYA University. It was build on 23 December 2010, along with the university opening. It provides the learning materials whether in printed form and digital form. It also provides up to date technology that help the users convenient in using the library services. \r\n\r\nThe field that focusing by this library is the Information Technology fields. This academic library provides both materials in printed form and digital form. The members are able to access to the library collection by using their own password and identification number that already have been given.\r\n\r\nThe NORLISYA system is a system that providing digital materials format for the library users. As the increases of the materials in the building, this library has taken initiative to make available the printed materials in digital form. \r\n\r\nIn addition, by providing materials in various formats, users are able to access to the materials at any time, any where and many people able to access to the materials at one time.\r\n\r\nthis system also will be used by the administration that is the library. It is because, the data, information and others elements in the system need to be update. In other words, this system is focusing to two groups, there are the library users and the administration\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `book_table`
--

CREATE TABLE IF NOT EXISTS `book_table` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ACCESSIONNO` varchar(50) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `TITLE` varchar(500) NOT NULL,
  `EDITION` varchar(10) NOT NULL,
  `PLACE` varchar(255) NOT NULL,
  `PUBLISHER` varchar(500) NOT NULL,
  `YEARPUB` varchar(4) NOT NULL,
  `PHYSICALDESC` varchar(500) NOT NULL,
  `SUBJECT` varchar(100) NOT NULL,
  `ISBN` varchar(20) NOT NULL,
  `TYPEITEM` varchar(20) NOT NULL,
  `CALLNO` varchar(50) NOT NULL,
  `ACTIVATION` varchar(50) NOT NULL,
  `PROCESS` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=102 ;

--
-- Dumping data for table `book_table`
--

INSERT INTO `book_table` (`ID`, `ACCESSIONNO`, `AUTHOR`, `TITLE`, `EDITION`, `PLACE`, `PUBLISHER`, `YEARPUB`, `PHYSICALDESC`, `SUBJECT`, `ISBN`, `TYPEITEM`, `CALLNO`, `ACTIVATION`, `PROCESS`) VALUES
(30, '000030', 'Pillai, Peter S.', 'Komputer peribadi 6: tahap tiga', '-', 'Kuala Lumpur', 'Utusan Pub.', '1996', '55p.: ill.; 26 cm.', 'Microcomputers', '9676105295', 'Book', 'QA76.5.P55 Jil.6 1996', 'Yes', 'End-User'),
(29, '000029', 'Long, Larry E', 'Introduction to computers and information systems', '4th. ed', 'Englewood Cliffs, NJ', 'Prentice Hall', '1994', '55p.: ill.; 26 cm.', 'Electronic data processing', '0134978846', 'Book', 'QA76.5 .P55 Jil.6 1996', 'Yes', 'End-User'),
(28, '000028', 'Parsons, June Jamrich', 'The Practical PC', '2nd ed', 'Australia', 'Course Technology', '2001', 'xvi, 217 p.: ill.; 28 cm +1 computer laser optical disc (4 3/4', 'Microcomputers', '0619020741', 'Book', 'C345. P32 2001', 'Yes', 'End-User'),
(31, '000031', 'Othman Shariff', 'Manual asas pemasangan komputer peribadi & internet', '-', 'Kuala Lumpur', 'Venton Publishing', '2001', 'ix, 204 ms.: il.; 25 cm.', 'Microcomputers', '9832031540', 'Book', 'QA76.5.P66 O84 2001', 'Yes', 'End-User'),
(32, '000032', 'Shelly, Gary B.', 'Study guide discovering computers 2000: concepts for a connected world', '-', 'Cambridge', 'Course Technology', '2000', 'xvi, 1v. (various pagings): ill.; 28 cm.', 'Computers', '0789546337', 'Book', 'QA76.5.S54 2000', 'Yes', 'End-User'),
(33, '000033', 'Parker, Charles S', 'Understanding computers: today and tomorrow', '2002 ed', 'Ft. Worth, TX', 'Dryden Press', '2002', '1 v. (unpaged): col. ill.; 28 cm.', 'Computers', '0030259681', 'Book', 'QA76.5.P37 2002', 'Yes', 'End-User'),
(34, '000034', 'Collings, Stephen', 'Computer concepts: an introduction', '-', 'Australia', 'South-Western Computer Education', '2001', 'x, 214 p.: ill. (some col.); 28 cm|e1 computer optical disc (4 3/4 in.)', 'Computers', '0538724226', 'Book', 'QA76.C65 2001', 'Yes', 'End-User'),
(35, '000035', 'Murdocca, Miles', 'Computer architecture and organization|ban integrated approach', '-', 'Hoboken, NJ', 'John Wiley & Sons', '2007', 'xx, 524 p.: ill.; 25 cm.', 'Computer architecture', '9780471733881', 'Book', 'QA76.9.A73 M87 2007', 'Yes', 'End-User'),
(37, '000037', 'Bartee, Thomas C.', 'Computer architecture and logic design', '-', 'New York', 'McGraw-Hill', '1991', 'xii, 628 p.: ill.; 25 cm.', 'Logic design', '007112554X', 'Book', 'QA76.9.A73 B37 1991', 'Yes', 'End-User'),
(39, '000039', 'Murdocca, Miles', 'Principles of computer architecture', '-', 'Upper Saddle River, NJ', 'Prentice Hall', '2000', 'xxi, 553 p.: ill. (some col.); 25 cm.', 'Computer architecture', '0201436647', 'Book', 'QA76.9.A73 M87 2000', 'Yes', 'End-User'),
(40, '000040', 'Stallings, William', 'Computer organization and architecture: designing for performance', '7th ed', 'Upper Saddle River', 'Prentice-Hall', '2006', 'xiv, 754 p.: ill.; 24cm.', 'Computer organization', '0132017504', 'Book', 'QA76.9.C643 S73 2006', 'Yes', 'End-User'),
(41, '000041', 'Williams, Rob', 'Computer systems architecture: a networking approach', '2nd ed', 'Harlow, England: New York', 'Pearson Prentice Hall', '2006', 'xxii, 730 p.: ill., 24 cm. ', 'Computer network architectures', '0321340795', 'Book', 'QA76.9.A73 W55 2006', 'Yes', 'End-User'),
(43, '000043', '-', 'Computerization and controversy: value conflicts and social choices', '2nd ed', 'San Diego', 'Academic Press', '1996', 'xxiv, 961 p.: 24 cm.', 'Computers and civilization', '0124150403', 'Book', 'QA76.9.C66 1996', 'Yes', 'End-User'),
(44, '000044', 'Brookshear, J. Glenn', 'Computer science: an overview', '8th ed', 'Boston', 'Pearson/Addison Wesley', '2005', 'xiv, 562 p.: ill. , graphs, tables; 23 cm.', 'Computer science', '0321247264', 'Book', 'QA76.B76 2005', 'Yes', 'End-User'),
(45, '000045', 'Kamin, Samuel N.', 'An Introduction to computer science using Java', '2nd ed', 'Boston', 'McGraw-Hill', '2002', 'xxix, 753 p.: ill. (some col.); 23 cm.', 'Java (Computer program language)', '007112232X', 'Book', 'QA76.K36 2002', 'Yes', 'End-User'),
(46, '000046', 'Mokhtar Hanafiah', 'Computer associates: Banks evaluating our proposal on IT', '-', 'Shah Alam, Selangor', 'The Star', '2001', '3p. : ill.', 'Information technologyâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(47, '000047', 'Li, Lam ', 'Computer games for NS.  Ongkili: Fun and unity the objective', '-', 'Shah Alam, Selangor', 'The Star', '2004', '3p. : ill.', 'Information technologyâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(48, '000048', 'Li, Lam ', 'Computer giant to broaden investment in Penang', '-', 'Shah Alam, Selangor', 'The Star', '2003', '3p. : ill.', 'Information technologyâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(49, '000049', 'Hamidah Atan and Serena Lau ', 'Computer glitch at NRD infuriates MyKad applicants', '-', 'Shah Alam, Selangor', 'The New Straits Time', '2003', '3p. : ill.', 'Computerâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(50, '000050', 'Jalina Joheng, A. Hafiz Yatim and Zarina Abdullah ', 'Computers seized from store-rooms', '-', 'Shah Alam, Selangor', 'The New Straits Time', '2003', '3p. : ill.', 'Computerâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(51, '000051', 'K.P. Waran ', 'Dr M: Learn new technologies', '-', 'Shah Alam, Selangor', 'The New Straits Time', '-', '3p. : ill.', 'Information technologyâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(52, '000052', '-', 'Tremendous growth: Trend to continue till 2007. EMC Computer confident of expanding 10pc annually', '-', 'Shah Alam, Selangor', 'The New Straits Time', '2003', '3p. : ill.', 'Computerâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(53, '000053', 'Steven Patrick ', 'Mak-Cik to bridge divide', '-', 'Shah Alam, Selangor', 'The Star', '2007', '3p. : ill.', 'Information technologyâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(54, '000054', '-', 'Malaysian PC users dodge faulty antivirus update', '-', 'Shah Alam, Selangor', 'The Star', '2000', '3p. : ill.', 'Information technologyâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(55, '000055', '-', 'Ministry to get list of â€˜unsafeâ€™ computer labs', '-', 'Shah Alam, Selangor', 'The New Straits Time', '2003', '3p. : ill.', 'Computerâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(56, '000056', '-', 'New computer loan cheer for civil servants', '-', 'Shah Alam, Selangor', 'The Star', '2010', '3p. : ill.', 'Computerâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(58, '000058', '-', 'Police want more cyber crime fighters', '-', 'Shah Alam, Selangor', 'The New Straits Time', '2003', '3p. : ill.', 'Information technologyâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(59, '000059', '-', 'RM 11,916 for one computer', '-', 'Shah Alam, Selangor', 'The Star', '2009', '3p. : ill.', 'Computerâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(61, '000061', 'Stephen Then ', 'RM 200m to create software with Malaysian touch', '-', 'Shah Alam, Selangor', 'The Star', '2010', '3p. : ill.', 'Information technologyâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(62, '000062', 'Sulok Tawie', 'School computer lab collapses during storm', '-', 'Shah Alam, Selangor', 'The New Straits Time', '2003', '3p. : ill.', 'Computerâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(63, '000063', '-', 'Cybersecurity training course aims for wider impact', '-', 'Malaysia', 'Star Publications', '2011', '3p. : ill.', 'Computerâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(64, '000064', '-', 'HP hits Tablet market with TouchPad', '-', 'Malaysia', 'Star Publications', '2011', '3p. : ill.', 'Information technologyâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(65, '000065', 'Hamidah Atan and Serena Lau ', 'Kaspersky tips Android to dominate mobile', '-', 'Malaysia', 'Star Publications', '2011', '3p. : ill.', 'Computerâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(66, '000066', 'Subashini Selvaratnam ', 'Security in a heartbeat', '-', 'Malaysia', 'Star Publications', '2011', '3p. : ill.', 'Information technologyâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(70, '000070', 'Oliva, Lawrence M.', 'IT security: advice from experts', '-', 'Hershey', 'CyberTech Pub', '2004', 'ix, 171 p.: ill.; 24 cm.', 'Computer security', '1591402476', 'Book', 'QA76.9.A25 O45 2004', 'Yes', 'End-User'),
(71, '000071', 'Hailperin, Max', 'Operating systems and middleware: supporting controlled interaction', '-', 'Australia', 'Thomson Course Technology', '2007', 'xxii, 474 p.: ill.; 25 cm.', 'Operating systems (Computers)', '0534423698', 'Book', 'QA76.76.M54 H35 2007', 'Yes', 'End-User'),
(72, '000072', 'Peter Thomas & Paul M. Rutter', 'A computer literacy skills profile of pharmacists residing in two counties of England', '-', 'United Kingdom', 'Health Libraries Group', '2008', '25, pp.288â€“294', 'Computer Literacy, England, Pharmacists', '-', 'OnlineJournal', 'doi: 10.1111/j.1471-1842.2008.00767.x', 'Yes', 'End-User'),
(73, '000073', 'C. Wecker ', 'Computer literacy and inquiry learning: when geeks learn less', '-', 'Germany', 'Blackwell Publishing Ltd', ' 200', '23, 133â€“144', 'Collaborative Learning', '-', 'OnlineJournal', 'doi: 10.1111/j.1365-2729.2006.00218.x', 'Yes', 'End-User'),
(74, '000074', 'Florence Martin, James D. Klein and Howard Sullivan', 'The impact of instructional elements in computer-based instruction', '-', 'USA', 'Blackwell Publishing', '2007', '38/ 4, 623â€“636', 'Computer instructional', '-', 'OnlineJournal', 'doi: 10.1111/j.1467-8535.2006.00670.x', 'Yes', 'End-User'),
(75, '000075', 'Mark Taylor, John Haggerty, David Gresty', 'The legal aspects of corporate computer usage policies', '-', '-', 'Elsevier Ltd.', '2010', '2 6, 7 2 â€“ 7 6', 'Computer usage policy', '-', 'OnlineJournal', 'doi: 10.1016/j.clsr.2009.11.007', 'Yes', 'End-User'),
(76, '000076', 'K.A. Owusu', 'Effects of computer-assisted instruction on performance of senior high school biology students in Ghana', '-', '-', 'Elsevier Ltd.', '2010', '55, 904â€“910', 'Computer-assisted instruction', '-', 'OnlineJournal', 'doi: 10.1016/j.compedu.2010.04.001', 'Yes', 'End-User'),
(77, '000077', 'S.E. Schulenberg, A.M.A. Melton', 'The Computer Aversion, Attitudes, and Familiarity Index (CAAFI): A validity study', '-', '-', 'Elsevier Ltd.', '2008', '24, 2620â€“2638', 'Computer anxiety', '-', 'OnlineJournal', 'doi: 10.1016/j.chb.2008.03.002', 'Yes', 'End-User'),
(78, '000078', 'S. Parayitam ', 'Computer attitude as a moderator in the relationship between computer anxiety, satisfaction, and stress', '-', '-', 'Elsevier Ltd.', '2010', '26, 345â€“352', 'Computer anxiety', '-', 'OnlineJournal', 'doi: 10.1016/j.chb.2009.11.005', 'Yes', 'End-User'),
(79, '000079', 'S. Korobili , A. Togia, A. Malliari', 'Computer anxiety and attitudes among undergraduate students in Greece', '-', '-', 'Elsevier Ltd.', '2010', '26, 399â€“405', 'Computer attitudes', '-', 'OnlineJournal', 'doi: 10.1016/j.chb.2009.11.011', 'Yes', 'End-User'),
(80, '000080', '-', '1, 217 school computer labs to be complete by December', '-', 'Shah Alam, Selangor', 'The New Straits Time', '2003', '3p. : ill.', 'Computerâ€”Periodicals', '-', 'Newspaper', '-', 'Yes', 'End-User'),
(82, '000082', 'Benson, Allen C.', 'Securing PCs and data in libraries and schools: a handbook with menuing, anti-virus, and other protective software', '-', 'New York', 'Neal-Schuman Publishers', '1997', 'xvi, 252 p.: ill.; 23 cm. +1 computer laser optical disc (4 3/4 in.)', 'Computer security', '1555703216', 'Book', 'QA76.9.A25|bB46 1997', 'Yes', 'End-User'),
(83, '000083', 'Pfleeger, Charles P., 1948-', 'Security in computing', '3rd ed', 'Upper Saddle River, NJ', 'Prentice Hall PTR', '2003', 'xxix, 746 p.: ill.; 25 cm.', 'Data protection', '0130355488', 'Book', 'QA76.9.A25 P45 2003', 'Yes', 'End-User'),
(84, '000084', 'Robertson, Ian T', 'IT skills for accounting students', '-', 'London', 'Pitman Publishing', '1996', 'viii, 471p.: ill.; 25cm.', 'Information technology', '0273617141', 'Book', 'HF5679 R36 1996', 'Yes', 'End-User'),
(85, '000085', 'Charles Cresson Wood', 'A computer emergency response team policy', '-', '-', 'Charles Cresson Wood ', '1996', '4/2, p. 4', 'Computer, Policy', '-', 'OnlineJournal', '-', 'Yes', 'End-User'),
(86, '000086', 'Jintae Lee , Younghwa Lee', 'A holistic model of computer abuse within organizations', '-', '-', 'MCB UP Limited', '2002', '10/2, 57-63', 'Computer, Computer Security, Theory', '-', 'OnlineJournal', '10.1108/09685220210424104', 'Yes', 'End-User'),
(87, '000087', 'Vasileios Vlachos and Diomidis Spinellis', 'A PRoactive malware identification system based on the computer hygiene principles', '-', '-', 'Emerald Group Publishing Limited', '2007', 'Vol. 15 No. 4, pp. 295-312', 'Computer networks, Data security, Computer viruses', '-', 'OnlineJournal', '10.1108/09685220710817815', 'Yes', 'End-User'),
(88, '000088', 'Charles Cresson Wood', 'Background checks for employees in computer-related positions of trust', '-', '-', 'MCB University Press Limited', '1995', 'Vol. 3 No. 5, pp. 21-22', 'Computer, Employees', '-', 'OnlineJournal', '-', 'Yes', 'End-User'),
(89, '000089', 'Karen A. Forcht, Robert G. Brookshire, Scott P. Stevens, and Rodney Clarke', 'The Computer Ethics of University Students: An International Exploratory Study', '-', '-', 'MCB University Press Limited', '1993', 'Vol. 1 No. 5, pp. 32-36', 'Computer, computer ethics', '-', 'OnlineJournal', '-', 'Yes', 'End-User'),
(90, '000090', 'Pilar Pazos & Mario G. Beruvides', 'Performance patterns in face-to-face and computer-supported teams', '-', '-', '-', '2011', 'Vol. 17 No. 1/2, pp. 83-101', 'Computer, Patterns', '-', 'OnlineJournal', 'DOI 10.1108/13527591111114729', 'Yes', 'End-User'),
(91, '000091', 'Rachid Zeffane', 'Computer Usage and Job Satisfaction: An Empirical Exploration', '-', '-', 'MCB University Press Limited', '1994', 'Vol. 2 No. 2 , pp. 10-22', 'Computer usage, computer', '-', 'OnlineJournal', '-', 'Yes', 'End-User'),
(92, '000092', 'S. Vinodh & D. Kuttalingam', 'Computer-aided design and engineering as enablers of agile manufacturing: A case study in an Indian manufacturing organization', '-', '-', 'Emerald Group Publishing Limited', '2011', 'Vol. 22 No. 3 pp. 405-418', 'Agile production, Computer aided design, India', '-', 'OnlineJournal', '10.1108/17410381111112747', 'Yes', 'End-User'),
(93, '000093', 'C. Bryan Foltz', 'Cyberterrorism, computer crime, and reality', '-', '-', 'Emerald Group Publishing Limited', '2004', 'Vol. 12 No. 2, pp. 154-166', 'Computer crime, Information systems, Data security, Terrorism', '-', 'OnlineJournal', '10.1108/09685220410530799', 'Yes', 'End-User'),
(94, '000094', 'H. Joseph Wen', 'Internet computer virus protection policy', '-', '-', 'MCB University Press', '1998', '6/2, 66â€“71', 'Computer, Virus', '-', 'OnlineJournal', '-', 'Yes', 'End-User'),
(95, '000095', 'Gurpreet Dhillon', 'Managing and controlling computer misuse ', '-', '-', 'MCB University Press', '1999', '7/4, pp.171-175', 'Computer fraud, Loss prevention, Computer security', '-', 'OnlineJournal', '-', 'Yes', 'End-User'),
(96, '000096', 'Kevin J. Fitzgerald', 'What if you had a flood in your computer room?', '-', '-', 'MCB University Press Limited', '1995', 'Vol. 3 No. 1, pp. 38-40', 'Computer', '-', 'OnlineJournal', '-', 'Yes', 'End-User'),
(97, '123', 'Zarul', 'Zarul', 'Zarul', 'Zarul', 'Zarul', '2012', 'Zarul', 'Zarul', '999999', 'OnlineJournal', 'Zarul', 'Yes', 'End-User'),
(98, '987654321', 'Jufri', 'Sabariah', '1', 'Merbok', 'UiTM', '2012', 'iv', 'Filem', '33333333', 'Book', 'QA166z145', 'Yes', 'End-User'),
(99, '4444', 'hamiza', 'hamiza', '1', '1', 'hamiza', '2012', '', 'library', '4444', 'Book', '', 'No', 'Cataloging'),
(100, '555', '555', '555', '555', '555', '555', '555', '', '555', '555', 'Book', '', 'No', 'Cataloging'),
(101, '88888', '88888', '88888', '88888', '88888', '88888', '2012', '', 'library', '88888', 'Book', '', 'No', 'Cataloging');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `ADDRESS` varchar(500) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PHONE_NO` varchar(50) NOT NULL,
  `POSITION` varchar(50) NOT NULL,
  `DEPARTMENT` varchar(50) NOT NULL,
  `USERNAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`ID`, `NAME`, `ADDRESS`, `EMAIL`, `PHONE_NO`, `POSITION`, `DEPARTMENT`, `USERNAME`, `PASSWORD`) VALUES
(1, 'Abd Latif Bin Abdul Rahman', 'Jelapang, Ipoh, Perak Darul Ridzuan', 'latif@ndls.edu.my', '04-54932012', 'Assistant Librarian', 'Cataloging', 'latif', 'qwerty123'),
(2, 'Abd Rahim Abdul Rahman', 'Kulim, Kedah', 'rahim@ndls.edu.my', '04-54932012', 'Chief Librarian', 'Acquisition', 'rahim', 'qwerty123'),
(3, 'Muhammad Hazim Zafri bin Abd Latif ', 'Manjung, Perak', 'hazim@ndls.edu.my', '04-54932012', 'Librarian', 'Circulation', 'hazim', 'qwerty123'),
(4, 'Nur Nabilah Najihah Binti Abd Latif', 'Kuala Lumpur', 'nabilah@ndls.edu.my', '04-54932012', 'Customer Service', 'Administration', 'nabilah', 'qwerty123');

-- --------------------------------------------------------

--
-- Table structure for table `questionnare_section_a`
--

CREATE TABLE IF NOT EXISTS `questionnare_section_a` (
  `qsa_id` int(11) NOT NULL AUTO_INCREMENT,
  `qsa_q1` varchar(45) DEFAULT NULL,
  `qsa_q2` varchar(45) DEFAULT NULL,
  `qsa_q3` varchar(45) DEFAULT NULL,
  `qsa_q4` varchar(45) DEFAULT NULL,
  `qsa_q5` varchar(45) DEFAULT NULL,
  `qsa_q6` varchar(45) DEFAULT NULL,
  `qsa_q7` varchar(45) DEFAULT NULL,
  `university_uni_id` int(11) NOT NULL,
  PRIMARY KEY (`qsa_id`,`university_uni_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=23 ;

--
-- Dumping data for table `questionnare_section_a`
--

INSERT INTO `questionnare_section_a` (`qsa_id`, `qsa_q1`, `qsa_q2`, `qsa_q3`, `qsa_q4`, `qsa_q5`, `qsa_q6`, `qsa_q7`, `university_uni_id`) VALUES
(1, 'Female', '18', 'Indian', 'Part Time', '2', '2', '3', 1),
(3, 'Female', '35', 'Malay', 'Full Time', '2', '1', '1', 1),
(4, 'Female', '35', 'Malay', 'Full Time', '2', '1', '1', 1),
(5, 'Female', '35', 'Malay', 'Full Time', '2', '1', '1', 1),
(6, 'Female', '35', 'Malay', 'Full Time', '2', '1', '1', 2),
(7, 'Female', '35', 'Chinese', 'Full Time', '2', '1', '1', 2),
(8, 'Male', '35', 'Chinese', 'Full Time', '2', '1', '1', 1),
(9, 'Male', '35', 'Chinese', 'Full Time', '2', '1', '1', 1),
(10, 'Male', '35', 'Others', 'Full Time', '2', '1', '1', 1),
(11, 'Female', '36', 'Chinese', 'Full Time', '1', '1', '1', 2),
(12, 'Male', '40', 'Malay', 'Full Time', '1', '8', '10', 1),
(13, 'Male', '33', 'Malay', 'Full Time', '1', '8', '10', 1),
(14, 'Male', '25', 'Malay', 'Full Time', '1', '1', '1', 1),
(15, 'Male', '40', 'Malay', 'Full Time', '1', '8', '8', 1),
(16, 'Male', '17', 'Malay', 'Full Time', '1', '8', '9', 1),
(17, 'Male', '40', 'Malay', 'Full Time', '1', '7', '9', 1),
(18, 'Male', '17', 'Malay', 'Full Time', '1', '1', '1', 1),
(19, 'Male', '17', 'Malay', 'Full Time', '1', '4', '7', 1),
(20, 'Male', '17', 'Malay', 'Full Time', '1', '4', '7', 1),
(21, 'Female', '17', 'Malay', 'Full Time', '2', '4', '5', 1),
(22, 'Female', '17', 'Malay', 'Full Time', '2', '4', '5', 1);

-- --------------------------------------------------------

--
-- Table structure for table `questionnare_section_b`
--

CREATE TABLE IF NOT EXISTS `questionnare_section_b` (
  `qsb_id` int(11) NOT NULL AUTO_INCREMENT,
  `qsb_q1` varchar(45) DEFAULT NULL,
  `qsb_q2` varchar(45) DEFAULT NULL,
  `qsb_q3` varchar(45) DEFAULT NULL,
  `qsb_q4` varchar(45) DEFAULT NULL,
  `qsb_q5` varchar(45) DEFAULT NULL,
  `qsb_q6` varchar(45) DEFAULT NULL,
  `qsb_q7` varchar(45) DEFAULT NULL,
  `university_uni_id` int(11) NOT NULL,
  PRIMARY KEY (`qsb_id`,`university_uni_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `questionnare_section_b`
--

INSERT INTO `questionnare_section_b` (`qsb_id`, `qsb_q1`, `qsb_q2`, `qsb_q3`, `qsb_q4`, `qsb_q5`, `qsb_q6`, `qsb_q7`, `university_uni_id`) VALUES
(1, '1', '2', '3', '4', '5', '6', '7', 1),
(2, '1', '2', '2', '2', '3', '3', '2', 1),
(3, '1', '2', '2', '2', '3', '3', '2', 1),
(4, '1', '2', '2', '2', '3', '3', '2', 1),
(5, '1', '2', '2', '2', '3', '3', '2', 2),
(6, '1', '2', '2', '2', '3', '3', '2', 2),
(7, '1', '2', '2', '2', '3', '3', '2', 1),
(8, '1', '2', '2', '2', '3', '3', '2', 1),
(9, '1', '2', '2', '2', '3', '3', '2', 1),
(10, '3', '4', '5', '6', '5', '4', '3', 2),
(11, '7', '7', '7', '7', '7', '7', '7', 1),
(12, '7', '7', '7', '7', '7', '7', '7', 1),
(13, '7', '7', '7', '7', '7', '7', '7', 1),
(14, '7', '7', '7', '7', '7', '7', '7', 1),
(15, '7', '7', '7', '7', '7', '7', '7', 1),
(16, '7', '7', '7', '7', '7', '7', '7', 1),
(17, '7', '7', '7', '7', '7', '7', '7', 1),
(18, '5', '5', '5', '5', '5', '3', '3', 1),
(19, '5', '5', '5', '5', '5', '3', '3', 1),
(20, '7', '7', '7', '7', '7', '7', '7', 1),
(21, '7', '7', '7', '7', '7', '7', '7', 1);

-- --------------------------------------------------------

--
-- Table structure for table `questionnare_section_c`
--

CREATE TABLE IF NOT EXISTS `questionnare_section_c` (
  `qsc_id` int(11) NOT NULL AUTO_INCREMENT,
  `qsc_q1` varchar(45) DEFAULT NULL,
  `qsc_q2` varchar(45) DEFAULT NULL,
  `qsc_q3` varchar(45) DEFAULT NULL,
  `qsc_q4` varchar(45) DEFAULT NULL,
  `qsc_q5` varchar(45) DEFAULT NULL,
  `qsc_q6` varchar(45) DEFAULT NULL,
  `qsc_q7` varchar(45) DEFAULT NULL,
  `qsc_q8` varchar(45) DEFAULT NULL,
  `qsc_q9` varchar(45) DEFAULT NULL,
  `university_uni_id` int(11) NOT NULL,
  PRIMARY KEY (`qsc_id`,`university_uni_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `questionnare_section_c`
--

INSERT INTO `questionnare_section_c` (`qsc_id`, `qsc_q1`, `qsc_q2`, `qsc_q3`, `qsc_q4`, `qsc_q5`, `qsc_q6`, `qsc_q7`, `qsc_q8`, `qsc_q9`, `university_uni_id`) VALUES
(1, '1', '2', '3', '4', '5', '6', '7', '6', '5', 1),
(2, '6', '6', '5', '7', '6', '5', '7', '6', '6', 1),
(3, '6', '6', '5', '7', '6', '5', '7', '6', '6', 1),
(4, '6', '6', '5', '7', '6', '5', '7', '6', '6', 1),
(5, '6', '6', '5', '7', '6', '5', '7', '6', '6', 2),
(6, '6', '2', '2', '3', '3', '2', '2', '2', '2', 2),
(7, '7', '7', '7', '7', '7', '6', '6', '6', '6', 1),
(8, '7', '7', '7', '7', '7', '6', '6', '6', '6', 1),
(9, '7', '7', '7', '7', '7', '6', '6', '6', '6', 1),
(10, '3', '2', '2', '3', '2', '3', '2', '2', '3', 2),
(11, '7', '7', '7', '7', '7', '7', '7', '7', '7', 1),
(12, '7', '7', '7', '7', '7', '7', '7', '7', '7', 1),
(13, '7', '7', '7', '7', '7', '7', '7', '7', '7', 1),
(14, '7', '7', '7', '7', '7', '7', '7', '7', '7', 1),
(15, '7', '7', '7', '7', '7', '7', '7', '7', '7', 1),
(16, '7', '7', '7', '7', '7', '7', '7', '7', '7', 1),
(17, '7', '7', '7', '7', '7', '7', '7', '7', '7', 1),
(18, '5', '3', '3', '7', '7', '3', '3', '3', '3', 1),
(19, '5', '3', '3', '7', '7', '3', '3', '3', '3', 1),
(20, '7', '7', '7', '7', '7', '7', '7', '7', '7', 1),
(21, '7', '7', '7', '7', '7', '7', '7', '7', '7', 1);

-- --------------------------------------------------------

--
-- Table structure for table `questionnare_section_d`
--

CREATE TABLE IF NOT EXISTS `questionnare_section_d` (
  `qsd_id` int(11) NOT NULL AUTO_INCREMENT,
  `qsd_q1` varchar(45) DEFAULT NULL,
  `qsd_q2` varchar(45) DEFAULT NULL,
  `qsd_q3` varchar(45) DEFAULT NULL,
  `qsd_q4` varchar(45) DEFAULT NULL,
  `qsd_q5` varchar(45) DEFAULT NULL,
  `qsd_q6` varchar(45) DEFAULT NULL,
  `qsd_q7` varchar(45) DEFAULT NULL,
  `qsd_q8` varchar(45) DEFAULT NULL,
  `qsd_q9` varchar(45) DEFAULT NULL,
  `qsd_q10` varchar(45) DEFAULT NULL,
  `university_uni_id` int(11) NOT NULL,
  PRIMARY KEY (`qsd_id`,`university_uni_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `questionnare_section_d`
--

INSERT INTO `questionnare_section_d` (`qsd_id`, `qsd_q1`, `qsd_q2`, `qsd_q3`, `qsd_q4`, `qsd_q5`, `qsd_q6`, `qsd_q7`, `qsd_q8`, `qsd_q9`, `qsd_q10`, `university_uni_id`) VALUES
(1, '1', '2', '3', '4', '5', '6', '7', '6', '5', '5', 1),
(4, '7', '7', '7', '6', '6', '6', '6', '5', '5', '5', 1),
(5, '7', '7', '7', '6', '6', '6', '6', '5', '5', '5', 1),
(6, '7', '7', '7', '4', '3', '2', '6', '3', '5', '2', 1),
(7, '7', '7', '7', '4', '3', '2', '6', '3', '5', '2', 2),
(8, '7', '7', '7', '4', '3', '2', '6', '3', '5', '2', 2),
(9, '7', '7', '7', '4', '3', '2', '6', '3', '5', '2', 1),
(10, '7', '7', '7', '4', '3', '2', '6', '3', '5', '2', 1),
(11, '7', '4', '6', '4', '6', '5', '5', '6', '5', '2', 1),
(12, '3', '2', '3', '3', '3', '3', '3', '3', '2', '3', 2),
(13, '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', 1),
(14, '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', 1),
(15, '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', 1),
(16, '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', 1),
(17, '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', 1),
(18, '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', 1),
(19, '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', 1),
(20, '6', '5', '6', '5', '5', '5', '4', '5', '5', '5', 1),
(21, '6', '5', '6', '5', '5', '5', '4', '5', '5', '5', 1),
(22, '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', 1),
(23, '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', 1);

-- --------------------------------------------------------

--
-- Table structure for table `questionnare_section_e`
--

CREATE TABLE IF NOT EXISTS `questionnare_section_e` (
  `qse_id` int(11) NOT NULL AUTO_INCREMENT,
  `qse_q1` varchar(45) DEFAULT NULL,
  `qse_q2` varchar(45) DEFAULT NULL,
  `qse_q3` varchar(45) DEFAULT NULL,
  `qse_q4` varchar(45) DEFAULT NULL,
  `qse_q5` varchar(45) DEFAULT NULL,
  `university_uni_id` int(11) NOT NULL,
  PRIMARY KEY (`qse_id`,`university_uni_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=23 ;

--
-- Dumping data for table `questionnare_section_e`
--

INSERT INTO `questionnare_section_e` (`qse_id`, `qse_q1`, `qse_q2`, `qse_q3`, `qse_q4`, `qse_q5`, `university_uni_id`) VALUES
(1, '1', '2', '3', '4', '5', 1),
(3, '5', '6', '5', '6', '7', 1),
(4, '5', '6', '5', '6', '7', 1),
(5, '5', '1', '5', '3', '4', 1),
(6, '5', '1', '5', '3', '4', 2),
(7, '5', '1', '5', '3', '4', 2),
(8, '5', '1', '5', '3', '4', 1),
(9, '5', '1', '5', '3', '4', 1),
(10, '5', '1', '5', '3', '4', 1),
(11, '3', '2', '3', '4', '5', 2),
(12, '7', '7', '7', '7', '7', 1),
(13, '7', '7', '7', '7', '7', 1),
(14, '7', '7', '7', '7', '7', 1),
(15, '7', '7', '7', '7', '7', 1),
(16, '7', '7', '7', '7', '7', 1),
(17, '7', '7', '7', '7', '7', 1),
(18, '7', '7', '7', '7', '7', 1),
(19, '5', '5', '5', '5', '5', 1),
(20, '5', '5', '5', '5', '5', 1),
(21, '7', '7', '7', '7', '7', 1),
(22, '7', '7', '7', '7', '7', 1);

-- --------------------------------------------------------

--
-- Table structure for table `questionnare_section_f`
--

CREATE TABLE IF NOT EXISTS `questionnare_section_f` (
  `qsf_id` int(11) NOT NULL AUTO_INCREMENT,
  `qsf_q1` varchar(45) DEFAULT NULL,
  `qsf_q2` varchar(45) DEFAULT NULL,
  `qsf_q3` varchar(45) DEFAULT NULL,
  `qsf_q4` varchar(45) DEFAULT NULL,
  `qsf_q5` varchar(45) DEFAULT NULL,
  `university_uni_id` int(11) NOT NULL,
  PRIMARY KEY (`qsf_id`,`university_uni_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `questionnare_section_f`
--

INSERT INTO `questionnare_section_f` (`qsf_id`, `qsf_q1`, `qsf_q2`, `qsf_q3`, `qsf_q4`, `qsf_q5`, `university_uni_id`) VALUES
(1, '1', '2', '3', '4', '5', 1),
(2, '7', '6', '6', '6', '6', 1),
(3, '7', '6', '6', '6', '6', 1),
(4, '1', '2', '1', '4', '4', 1),
(5, '1', '2', '1', '4', '4', 2),
(6, '1', '2', '1', '4', '4', 2),
(7, '1', '2', '1', '4', '4', 1),
(8, '1', '2', '1', '4', '4', 1),
(9, '1', '2', '1', '4', '4', 1),
(10, '7', '7', '6', '6', '5', 2),
(11, '7', '7', '7', '7', '7', 1),
(12, '7', '7', '7', '7', '7', 1),
(13, '7', '7', '7', '7', '7', 1),
(14, '7', '7', '7', '7', '7', 1),
(15, '7', '7', '7', '7', '7', 1),
(16, '7', '7', '7', '7', '7', 1),
(17, '7', '7', '7', '7', '7', 1),
(18, '5', '4', '5', '5', '5', 1),
(19, '5', '4', '5', '5', '5', 1),
(20, '7', '7', '7', '7', '7', 1),
(21, '7', '7', '7', '7', '7', 1);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE IF NOT EXISTS `staff` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `ADDRESS` varchar(500) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PHONE_NO` varchar(50) NOT NULL,
  `POSITION` varchar(50) NOT NULL,
  `DEPARTMENT` varchar(50) NOT NULL,
  `USERNAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`ID`, `NAME`, `ADDRESS`, `EMAIL`, `PHONE_NO`, `POSITION`, `DEPARTMENT`, `USERNAME`, `PASSWORD`) VALUES
(1, 'Jufri Jaafar', 'Jelapang, Ipoh, Perak Darul Ridzuan', 'latif@ndls.edu.my', '04-54932012', 'Assistant Librarian', 'Cataloging', 'Jufri', 'qwerty123'),
(2, 'Arif Idrus', 'Kulim, Kedah', 'rahim@ndls.edu.my', '04-54932012', 'Chief Librarian', 'Acquisition', 'Arif', 'qwerty123'),
(3, 'Arman Salman', 'Manjung, Perak', 'hazim@ndls.edu.my', '04-54932012', 'Librarian', 'Circulation', 'Arman', 'qwerty123'),
(4, 'Afiqah Afiq', 'Kuala Lumpur', 'nabilah@ndls.edu.my', '04-54932012', 'Customer Service', 'Administration', 'Afiq', 'qwerty123');

-- --------------------------------------------------------

--
-- Table structure for table `stafflibrary`
--

CREATE TABLE IF NOT EXISTS `stafflibrary` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `ADDRESS` varchar(500) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PHONE_NO` varchar(50) NOT NULL,
  `POSITION` varchar(50) NOT NULL,
  `DEPARTMENT` varchar(50) NOT NULL,
  `USERNAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `stafflibrary`
--

INSERT INTO `stafflibrary` (`ID`, `NAME`, `ADDRESS`, `EMAIL`, `PHONE_NO`, `POSITION`, `DEPARTMENT`, `USERNAME`, `PASSWORD`) VALUES
(1, 'Abd Latif Abdul Rahman', 'Jelapang, Ipoh, Perak Darul Ridzuan', 'latif@ndls.edu.my', '04-54932012', 'Assistant Librarian', 'USM', 'latif', 'qwerty123'),
(2, 'Arif Idrus', 'Kulim, Kedah', 'rahim@ndls.edu.my', '04-54932012', 'Chief Librarian', 'UPM', 'Arif', 'qwerty123'),
(3, 'Arman Salman', 'Manjung, Perak', 'hazim@ndls.edu.my', '04-54932012', 'Librarian', 'UM', 'Arman', 'qwerty123'),
(4, 'Afiqah Afiq', 'Kuala Lumpur', 'nabilah@ndls.edu.my', '04-54932012', 'Customer Service', 'UKM', 'Afiq', 'qwerty123');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `ADDRESS` varchar(500) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PHONE_NO` varchar(50) NOT NULL,
  `POSITION` varchar(50) NOT NULL,
  `DEPARTMENT` varchar(50) NOT NULL,
  `USERNAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`ID`, `NAME`, `ADDRESS`, `EMAIL`, `PHONE_NO`, `POSITION`, `DEPARTMENT`, `USERNAME`, `PASSWORD`) VALUES
(1, 'Jufri Jaafar', 'Jelapang, Ipoh, Perak Darul Ridzuan', 'latif@ndls.edu.my', '04-54932012', 'Assistant Librarian', 'Cataloging', 'Jufri', 'qwerty123'),
(2, 'Arif Idrus', 'Kulim, Kedah', 'rahim@ndls.edu.my', '04-54932012', 'Chief Librarian', 'Acquisition', 'Arif', 'qwerty123'),
(3, 'Arman Salman', 'Manjung, Perak', 'hazim@ndls.edu.my', '04-54932012', 'Librarian', 'Circulation', 'Arman', 'qwerty123'),
(4, 'Afiqah Afiq', 'Kuala Lumpur', 'nabilah@ndls.edu.my', '04-54932012', 'Customer Service', 'Administration', 'Afiq', 'qwerty123');

-- --------------------------------------------------------

--
-- Table structure for table `uitm_keda_g1_respondents`
--

CREATE TABLE IF NOT EXISTS `uitm_keda_g1_respondents` (
  `PID` int(11) NOT NULL AUTO_INCREMENT,
  `Firstname` char(15) DEFAULT NULL,
  `Lastname` char(15) DEFAULT NULL,
  `Race` char(15) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL,
  `Gender` int(11) NOT NULL,
  `Q1` int(11) NOT NULL,
  `Q2` int(11) NOT NULL,
  `Q3` int(11) NOT NULL,
  `Q4` int(11) NOT NULL,
  `Q5` int(11) NOT NULL,
  `Q6` int(11) NOT NULL,
  `Q7` int(11) NOT NULL,
  `Q8` int(11) NOT NULL,
  `Q9` int(11) NOT NULL,
  `Q10` int(11) NOT NULL,
  `Q11` int(11) NOT NULL,
  `Q12` int(11) NOT NULL,
  `Q13` int(11) NOT NULL,
  `Q14` int(11) NOT NULL,
  `Q15` int(11) NOT NULL,
  `Q16` int(11) NOT NULL,
  `Q17` int(11) NOT NULL,
  `Q18` int(11) NOT NULL,
  `Q19` int(11) NOT NULL,
  `Q20` int(11) NOT NULL,
  `Q21` int(11) NOT NULL,
  `Q22` int(11) NOT NULL,
  `Q23` int(11) NOT NULL,
  `Q24` int(11) NOT NULL,
  `Q25` int(11) NOT NULL,
  PRIMARY KEY (`PID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `uitm_keda_g1_respondents`
--

INSERT INTO `uitm_keda_g1_respondents` (`PID`, `Firstname`, `Lastname`, `Race`, `Age`, `Gender`, `Q1`, `Q2`, `Q3`, `Q4`, `Q5`, `Q6`, `Q7`, `Q8`, `Q9`, `Q10`, `Q11`, `Q12`, `Q13`, `Q14`, `Q15`, `Q16`, `Q17`, `Q18`, `Q19`, `Q20`, `Q21`, `Q22`, `Q23`, `Q24`, `Q25`) VALUES
(2, 'Amir', 'Hamzah', 'Malay', 29, 1, 3, 2, 1, 3, 2, 1, 3, 3, 1, 2, 2, 2, 3, 1, 4, 3, 2, 3, 3, 2, 1, 3, 3, 3, 2),
(3, 'Maniam', 'Muthuhendra', 'India', 30, 1, 4, 3, 2, 3, 2, 3, 1, 2, 1, 3, 3, 2, 2, 3, 2, 1, 2, 1, 2, 2, 2, 1, 2, 3, 3),
(4, 'Neeshantin', 'Varnakam', 'India', 32, 2, 3, 3, 1, 2, 3, 4, 1, 3, 4, 3, 3, 3, 2, 1, 3, 3, 4, 3, 1, 3, 4, 2, 3, 2, 4),
(5, 'Shamisha', 'Sweetie', 'India', 28, 2, 3, 4, 2, 4, 1, 2, 1, 3, 2, 3, 2, 2, 3, 1, 3, 2, 2, 3, 2, 3, 1, 3, 2, 3, 3),
(6, 'Vaniam', 'Murugam', 'India', 36, 1, 4, 3, 4, 2, 4, 3, 1, 2, 1, 2, 1, 1, 1, 2, 2, 2, 1, 2, 2, 1, 2, 2, 3, 2, 1),
(7, 'Balqis', 'Nasir', 'Malay', 26, 2, 4, 3, 2, 4, 2, 2, 1, 1, 1, 1, 2, 2, 3, 2, 1, 2, 3, 3, 2, 3, 2, 1, 3, 2, 1),
(8, 'Darman', 'Derman', 'Malay', 21, 1, 3, 3, 2, 1, 2, 1, 2, 1, 3, 2, 2, 2, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 1, 2),
(9, 'Wong', 'Louis Chan', 'Chinese', 26, 1, 3, 2, 1, 2, 3, 2, 3, 1, 1, 2, 2, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 1, 2),
(10, 'Lee', 'Ah Meng', 'Chinese', 37, 1, 2, 3, 2, 2, 2, 1, 2, 1, 2, 1, 3, 2, 1, 2, 3, 2, 2, 2, 3, 2, 1, 2, 2, 1, 2),
(14, 'Lily', 'Amsyar', 'Malay', 26, 2, 4, 4, 4, 3, 4, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 3, 4, 4, 4, 3, 3, 4, 4, 3, 4);

-- --------------------------------------------------------

--
-- Table structure for table `uitm_keda_g1_staff`
--

CREATE TABLE IF NOT EXISTS `uitm_keda_g1_staff` (
  `PID` int(11) NOT NULL AUTO_INCREMENT,
  `Firstname` char(15) DEFAULT NULL,
  `Lastname` char(15) DEFAULT NULL,
  `Race` char(15) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL,
  `Position` varchar(20) NOT NULL,
  `Department` varchar(20) NOT NULL,
  `Username` varchar(11) NOT NULL,
  `Password` int(11) NOT NULL,
  PRIMARY KEY (`PID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `uitm_keda_g1_staff`
--

INSERT INTO `uitm_keda_g1_staff` (`PID`, `Firstname`, `Lastname`, `Race`, `Age`, `Position`, `Department`, `Username`, `Password`) VALUES
(1, 'Khairul Fahmi', 'Che Mat', 'Malay', 30, 'Doctor', 'Emergency', 'Khairul', 1234),
(2, 'Nazim', 'Othman', 'Malay', 29, 'Pharmacist', 'Pharmacy', 'Nazim', 1234),
(3, 'Jackie', 'Chan', 'Chinese', 34, 'Doctor', 'SpecialistClinics', 'Jackie', 1234),
(4, 'Nor Fatinah', 'Othman', 'Malay', 25, 'IT Officer 1', 'Admin', 'Fatinah', 1234),
(5, 'Noorzilawati', 'Che Mat', 'Malay', 25, 'IT Officer 2', 'Admin', 'Zila', 1234),
(6, 'Nuratiqah', 'Hatta', 'Malay', 25, 'IT Officer 3', 'Admin', 'Atiqah', 1234),
(9, 'Farisha', 'Ezrakil', 'Malay', 29, 'Pharmacist', 'Pharmacy', 'Farisha', 1234),
(10, 'Mahesh', 'Sathia', 'India', 31, 'Doctor', 'SpecialistClinics', 'Mahesh', 1234);

-- --------------------------------------------------------

--
-- Table structure for table `uitm_keda_g2_persons`
--

CREATE TABLE IF NOT EXISTS `uitm_keda_g2_persons` (
  `PID` int(11) NOT NULL AUTO_INCREMENT,
  `Firstname` char(15) NOT NULL,
  `Lastname` char(15) NOT NULL,
  `Age` int(11) NOT NULL,
  `Gender` int(11) NOT NULL,
  `Q1` int(11) NOT NULL,
  `Q2` int(11) NOT NULL,
  `Q3` int(11) NOT NULL,
  `Q4` int(11) NOT NULL,
  `Q5` int(11) NOT NULL,
  `Q6` int(11) NOT NULL,
  `Q7` int(11) NOT NULL,
  `Q8` int(11) NOT NULL,
  `Q9` int(11) NOT NULL,
  `Q10` int(11) NOT NULL,
  `Q11` int(11) NOT NULL,
  `Q12` int(11) NOT NULL,
  `Q13` int(11) NOT NULL,
  `Q14` int(11) NOT NULL,
  `Q15` int(11) NOT NULL,
  `Q16` int(11) NOT NULL,
  `Q17` int(11) NOT NULL,
  `Q18` int(11) NOT NULL,
  `Q19` int(11) NOT NULL,
  `Q20` int(11) NOT NULL,
  `Q21` int(11) NOT NULL,
  `Q22` int(11) NOT NULL,
  `Q23` int(11) NOT NULL,
  `Q24` int(11) NOT NULL,
  `Q25` int(11) NOT NULL,
  `Q26` int(11) NOT NULL,
  PRIMARY KEY (`PID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=63 ;

--
-- Dumping data for table `uitm_keda_g2_persons`
--

INSERT INTO `uitm_keda_g2_persons` (`PID`, `Firstname`, `Lastname`, `Age`, `Gender`, `Q1`, `Q2`, `Q3`, `Q4`, `Q5`, `Q6`, `Q7`, `Q8`, `Q9`, `Q10`, `Q11`, `Q12`, `Q13`, `Q14`, `Q15`, `Q16`, `Q17`, `Q18`, `Q19`, `Q20`, `Q21`, `Q22`, `Q23`, `Q24`, `Q25`, `Q26`) VALUES
(1, 'Ameraa', 'Ramli', 27, 2, 3, 5, 5, 2, 3, 4, 4, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 3, 4, 5, 4, 3, 4, 5, 4, 3),
(3, 'Aiman', 'Hussein', 21, 1, 5, 5, 5, 1, 3, 4, 4, 5, 3, 3, 4, 3, 5, 3, 4, 3, 4, 4, 3, 4, 4, 3, 4, 4, 5, 5),
(4, 'Kamarulzaman', 'Rahim', 23, 1, 1, 5, 5, 1, 3, 4, 4, 3, 4, 3, 4, 5, 4, 3, 4, 4, 3, 5, 3, 3, 4, 5, 4, 1, 3, 3),
(5, 'Arina', 'Fauzi', 21, 2, 2, 1, 1, 4, 2, 3, 3, 3, 1, 3, 2, 2, 3, 1, 1, 2, 3, 3, 2, 1, 2, 2, 1, 2, 2, 1),
(6, 'Nik', 'Hamidi', 22, 1, 3, 2, 2, 4, 1, 2, 2, 2, 2, 3, 2, 1, 2, 3, 2, 1, 2, 2, 1, 2, 1, 2, 1, 3, 2, 1),
(7, 'Ashikin', 'Mat Ikram', 18, 2, 4, 3, 4, 3, 2, 3, 2, 2, 3, 2, 1, 3, 1, 2, 1, 2, 2, 2, 3, 2, 2, 1, 2, 2, 3, 2),
(8, 'Liyana', 'Helmi', 24, 2, 5, 4, 3, 2, 3, 4, 5, 4, 3, 3, 4, 5, 4, 3, 3, 4, 4, 3, 4, 3, 3, 4, 4, 3, 4, 4),
(9, 'Shahida', 'Sayuti', 26, 2, 5, 2, 1, 5, 2, 1, 2, 2, 1, 2, 2, 2, 3, 2, 2, 2, 3, 3, 2, 2, 3, 2, 2, 3, 1, 2),
(10, 'Mohd Syafiq', 'Asri', 44, 1, 5, 5, 4, 2, 4, 4, 3, 3, 4, 4, 5, 3, 4, 4, 3, 4, 4, 3, 4, 3, 4, 3, 4, 4, 3, 4),
(11, 'Mastura', 'Nadzir', 27, 2, 5, 5, 4, 2, 3, 4, 3, 3, 4, 4, 5, 4, 4, 4, 4, 3, 4, 4, 3, 4, 5, 4, 4, 4, 5, 5),
(12, 'Dayana', 'Fahmi', 19, 2, 5, 4, 4, 2, 1, 3, 4, 3, 4, 4, 4, 5, 4, 4, 5, 3, 4, 4, 4, 3, 4, 4, 5, 4, 3, 4),
(13, 'Rizam', 'Ramli', 28, 1, 5, 1, 1, 4, 1, 2, 2, 1, 2, 1, 2, 2, 2, 3, 3, 2, 3, 3, 3, 2, 3, 3, 2, 3, 1, 2),
(14, 'Rashid', 'Sibir', 35, 1, 5, 5, 5, 1, 5, 5, 5, 5, 5, 4, 5, 5, 5, 4, 4, 5, 5, 5, 5, 4, 4, 5, 4, 4, 4, 5),
(15, 'Nur Katrina', 'Aziz', 20, 2, 5, 2, 3, 4, 3, 2, 2, 3, 1, 3, 3, 2, 3, 3, 2, 3, 3, 3, 2, 2, 2, 1, 1, 2, 2, 3),
(16, 'Wawa', 'Zainal', 27, 2, 5, 5, 4, 2, 3, 3, 4, 5, 5, 4, 4, 3, 3, 4, 4, 5, 5, 3, 3, 4, 5, 4, 3, 3, 4, 4),
(17, 'Ehsan', 'Ismail', 30, 1, 5, 2, 3, 4, 3, 2, 3, 4, 3, 3, 3, 4, 3, 2, 3, 3, 3, 4, 3, 2, 3, 3, 3, 3, 4, 3),
(18, 'Ameraa', 'Rejab', 23, 2, 5, 2, 3, 4, 3, 3, 3, 4, 4, 3, 3, 4, 2, 3, 3, 3, 2, 3, 3, 4, 3, 2, 3, 4, 4, 3),
(19, 'Khairul', 'Hilman', 21, 1, 5, 2, 2, 3, 2, 2, 2, 3, 3, 2, 2, 3, 3, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 3, 3, 3),
(20, 'Intan', 'Ladyana', 21, 2, 5, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 1),
(21, 'Kamal', 'Adli', 21, 1, 5, 3, 3, 2, 3, 3, 2, 2, 3, 3, 2, 3, 2, 3, 2, 2, 3, 3, 2, 2, 3, 3, 2, 2, 3, 3),
(22, 'Khairul', 'Bakhtiar', 30, 1, 5, 3, 4, 5, 4, 3, 3, 2, 2, 2, 2, 3, 3, 2, 2, 2, 2, 3, 3, 2, 2, 3, 3, 3, 3, 2),
(23, 'Ameraa', 'Ahmad', 24, 2, 5, 4, 4, 1, 4, 4, 3, 4, 3, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 3, 4, 4, 4, 3, 3, 4),
(24, 'Latif', 'Rahman', 25, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3),
(28, 'Ameraa', 'Ahmad', 24, 2, 1, 4, 4, 1, 4, 4, 3, 4, 3, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 3, 4, 4, 4, 3, 3, 4),
(27, 'Ameraa', 'Ahmad', 24, 2, 1, 4, 4, 1, 4, 4, 3, 4, 3, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 3, 4, 4, 4, 3, 3, 4),
(29, 'Ameraa', 'Ahmad', 24, 2, 1, 4, 4, 1, 4, 4, 3, 4, 3, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 3, 4, 4, 4, 3, 3, 4),
(30, 'Ameraa', 'Ahmad', 24, 2, 1, 4, 4, 1, 4, 4, 3, 4, 3, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 3, 4, 4, 4, 3, 3, 4),
(31, 'Ameraa', 'Ahmad', 24, 2, 1, 4, 4, 1, 4, 4, 3, 4, 3, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 3, 4, 4, 4, 3, 3, 4),
(32, 'Ameraa', 'Ahmad', 24, 2, 4, 4, 4, 1, 4, 4, 3, 4, 3, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 3, 4, 4, 4, 3, 3, 4),
(33, 'Ameraa', 'Ahmad', 24, 2, 3, 4, 4, 1, 4, 4, 3, 4, 3, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 3, 4, 4, 4, 3, 3, 4),
(34, 'Ameraa', 'Ahmad', 24, 2, 4, 4, 4, 1, 4, 4, 3, 4, 3, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 3, 4, 4, 4, 3, 3, 4),
(35, 'Ameraa', 'Ahmad', 24, 2, 4, 4, 4, 1, 4, 4, 3, 4, 3, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 3, 4, 4, 4, 3, 3, 4),
(36, 'Ameraa', 'Ahmad', 24, 2, 2, 4, 4, 1, 4, 4, 3, 4, 3, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4, 3, 4, 4, 4, 3, 3, 4),
(45, 'LATIF', 'LATIF', 35, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(44, 'LATIF', 'LATIF', 35, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
(39, 'LATIF', 'LATIF', 33, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5),
(40, 'LATIF', 'LATIF', 33, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5),
(41, 'LATIF', 'LATIF', 34, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5),
(42, 'LATIF', 'LATIF', 35, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5),
(43, 'latif', 'rahman', 40, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5),
(46, 'LATIF', 'LATIF', 35, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(47, 'LATIF', 'LATIF', 35, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(48, 'LATIF', 'LATIF', 35, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(49, 'LATIF', 'LATIF', 35, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(50, 'LATIF', 'LATIF', 35, 1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(51, 'LATIF', 'LATIF', 35, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(52, 'LATIF', 'LATIF', 35, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(53, 'LATIF', 'LATIF', 35, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(54, 'LATIF', 'LATIF', 35, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(55, 'LATIF', 'LATIF', 35, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(56, 'LATIF', 'LATIF', 35, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(57, 'LATIF', 'LATIF', 58, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(58, 'ali', 'abu', 55, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(59, 'hhh', 'hhh', 77, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(60, 'latif', 'hazim', 22, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
(61, 'latif', 'hazim', 22, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(62, 'qq', 'qq', 22, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `uitm_keda_g2_staff`
--

CREATE TABLE IF NOT EXISTS `uitm_keda_g2_staff` (
  `PID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` char(30) DEFAULT NULL,
  `Position` char(30) DEFAULT NULL,
  `Department` char(30) DEFAULT NULL,
  `Username` char(15) DEFAULT NULL,
  `Password` int(11) DEFAULT NULL,
  `Nphone` varchar(11) NOT NULL,
  PRIMARY KEY (`PID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `uitm_keda_g2_staff`
--

INSERT INTO `uitm_keda_g2_staff` (`PID`, `Name`, `Position`, `Department`, `Username`, `Password`, `Nphone`) VALUES
(1, 'Azila', 'System Administrator', 'Information Technology', 'azila', 123456, '014-3450019'),
(2, 'Ily Mahfuzah', 'Community Financial Officer', 'Community Financial Service', 'ily', 789012, '013-4119792'),
(3, 'Faqrulradzy', 'Customer Service Manager', 'Customer Service', 'Faqrul', 345678, '016-5670023'),
(4, 'Nurul Husna', 'Enterprise Transformation Ofii', 'Enterprise Transformation Serv', 'husna', 900123, '014-6572314'),
(7, 'Dayana', 'Clerk', 'Customer Srvice', 'dayana', 555666, '017-2346781'),
(8, 'Rais', 'System Administrator Asistant', 'Information Technology', 'rais', 222111, '012-3456789');

-- --------------------------------------------------------

--
-- Table structure for table `uitm_keda_g3_staffs`
--

CREATE TABLE IF NOT EXISTS `uitm_keda_g3_staffs` (
  `PID` int(11) NOT NULL,
  `Firstname` char(30) DEFAULT NULL,
  `Lastname` char(30) DEFAULT NULL,
  `Gender` text,
  `Address` longtext NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Phone_No` varchar(11) NOT NULL,
  `Department` varchar(30) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Password` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `uitm_keda_g3_staffs`
--

INSERT INTO `uitm_keda_g3_staffs` (`PID`, `Firstname`, `Lastname`, `Gender`, `Address`, `Email`, `Phone_No`, `Department`, `Username`, `Password`) VALUES
(2, 'Razan', 'Fadzil', '2', 'No. 399A, Sungai Petani', 'joel_jan90@yahoo.com', '016-6457876', 'Financial', 'astro2', 123456),
(3, 'Nur Hidayah', 'Md. Jusoh', '2', 'Bukit Mertajam, Pulau Pinang', 'cikmontel22@gmail.com', '017-4484634', 'Manager', 'astro', 123456),
(5, 'Ahmad Fakhrul', 'Mohammad Nizam', '1', '1/11 Lorong Ahmad Kasam, Kuala Lumpur', 'fakh_78@gmail.com', '017-5343028', 'Admin', 'astro4', 123456),
(4, 'Nik Nor Marini', 'Nik Zainal', '2', 'No. 45B, Lorong Segamat, Johor', 'mar_21@yahoo.com', '014-9874632', 'Customer Service', 'astro3', 123456),
(1, 'Shahruhaida Adayu', 'Mohd Paili', '2', 'No. 131 Felda Chiku 07', 'ayurin_1403@yahoo.com', '013-8145477', 'Promotion', 'astro1', 123456);

-- --------------------------------------------------------

--
-- Table structure for table `uitm_keda_g3_users`
--

CREATE TABLE IF NOT EXISTS `uitm_keda_g3_users` (
  `Firstname` char(30) DEFAULT NULL,
  `Lastname` char(30) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL,
  `Gender` int(11) NOT NULL,
  `Race` int(11) NOT NULL,
  `Religion` int(11) NOT NULL,
  `Occupationsector` int(11) NOT NULL,
  `B1` int(11) NOT NULL,
  `B2` int(11) NOT NULL,
  `B3` int(11) NOT NULL,
  `B4` int(11) NOT NULL,
  `C1` int(11) NOT NULL,
  `C2` int(11) NOT NULL,
  `C3` int(11) NOT NULL,
  `C4` int(11) NOT NULL,
  `C5` int(11) NOT NULL,
  `C6` int(11) NOT NULL,
  `C7` int(11) NOT NULL,
  `C8` int(11) NOT NULL,
  `C9` int(11) NOT NULL,
  `C10` int(11) NOT NULL,
  `D1` int(11) NOT NULL,
  `D2` int(11) NOT NULL,
  `D3` int(11) NOT NULL,
  `D4` int(11) NOT NULL,
  `D5` int(11) NOT NULL,
  `D6` int(11) NOT NULL,
  `D7` int(11) NOT NULL,
  `D8` int(11) NOT NULL,
  `D9` int(11) NOT NULL,
  `D10` int(11) NOT NULL,
  `E1` int(11) NOT NULL,
  `E2` int(11) NOT NULL,
  `E3` int(11) NOT NULL,
  `E4` int(11) NOT NULL,
  `E5` int(11) NOT NULL,
  `E6` int(11) NOT NULL,
  `E7` int(11) NOT NULL,
  `E8` int(11) NOT NULL,
  `F1` int(11) NOT NULL,
  `F2` int(11) NOT NULL,
  `F3` int(11) NOT NULL,
  `F4` int(11) NOT NULL,
  `F5` int(11) NOT NULL,
  `F6` int(11) NOT NULL,
  `F7` int(11) NOT NULL,
  `F8` int(11) NOT NULL,
  `F9` int(11) NOT NULL,
  `F10` int(11) NOT NULL,
  `F11` int(11) NOT NULL,
  `F12` int(11) NOT NULL,
  `F13` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `uitm_keda_g3_users`
--

INSERT INTO `uitm_keda_g3_users` (`Firstname`, `Lastname`, `Age`, `Gender`, `Race`, `Religion`, `Occupationsector`, `B1`, `B2`, `B3`, `B4`, `C1`, `C2`, `C3`, `C4`, `C5`, `C6`, `C7`, `C8`, `C9`, `C10`, `D1`, `D2`, `D3`, `D4`, `D5`, `D6`, `D7`, `D8`, `D9`, `D10`, `E1`, `E2`, `E3`, `E4`, `E5`, `E6`, `E7`, `E8`, `F1`, `F2`, `F3`, `F4`, `F5`, `F6`, `F7`, `F8`, `F9`, `F10`, `F11`, `F12`, `F13`) VALUES
('Fadhli Syakirin', 'Mohamad Razi', 23, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 3, 2, 2, 2, 5, 4, 4, 5, 2, 2, 1, 3, 4, 4, 3, 3, 4, 5, 4, 3, 5, 4, 5, 4, 4, 5, 2, 3, 2, 3, 5, 5, 4, 4, 3, 4, 4, 3, 3),
('Shella', 'a/k Dana', 26, 2, 4, 2, 2, 2, 6, 4, 3, 1, 2, 5, 1, 1, 2, 4, 3, 4, 1, 2, 2, 4, 4, 2, 4, 5, 4, 5, 4, 5, 4, 2, 4, 5, 3, 3, 5, 1, 4, 0, 4, 4, 5, 4, 5, 5, 4, 4, 4, 4),
('Paul Carling', 'Rahit', 36, 1, 5, 2, 2, 2, 4, 3, 3, 1, 3, 5, 3, 2, 4, 5, 4, 4, 3, 2, 1, 3, 2, 5, 4, 5, 4, 4, 4, 3, 4, 3, 4, 4, 3, 3, 4, 4, 4, 2, 4, 4, 3, 4, 4, 3, 4, 4, 5, 4),
('Ahmad Danial', 'Ahmad', 35, 1, 1, 1, 3, 1, 5, 1, 1, 2, 2, 1, 1, 4, 3, 4, 3, 5, 4, 2, 1, 2, 1, 2, 3, 3, 3, 4, 3, 3, 3, 4, 3, 4, 4, 5, 5, 2, 3, 4, 5, 5, 4, 4, 5, 4, 5, 4, 5, 4),
('Izwani', 'Mohd Izani', 45, 2, 1, 1, 1, 2, 1, 4, 3, 1, 1, 4, 1, 4, 3, 4, 4, 5, 5, 2, 1, 1, 4, 4, 5, 4, 4, 5, 4, 4, 4, 3, 4, 4, 5, 4, 5, 2, 3, 4, 4, 5, 4, 4, 4, 5, 4, 5, 3, 5),
('Nur Nadia', 'Ibrahim', 47, 2, 1, 1, 3, 2, 4, 4, 3, 1, 2, 2, 2, 4, 4, 5, 5, 5, 4, 2, 2, 2, 1, 4, 5, 4, 5, 4, 5, 4, 5, 4, 4, 5, 5, 4, 5, 4, 5, 4, 4, 5, 5, 5, 4, 5, 4, 5, 5, 4),
('Lee Chong', 'Wah', 36, 1, 2, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 5, 4, 5, 4, 4, 4, 2, 1, 4, 2, 5, 5, 5, 5, 5, 5, 3, 4, 4, 5, 4, 5, 4, 5, 5, 4, 5, 4, 5, 4, 4, 5, 5, 4, 5, 5, 5),
('Kapoor', 'Chigum', 54, 2, 3, 3, 2, 2, 2, 5, 3, 1, 1, 4, 2, 5, 4, 4, 4, 5, 5, 2, 2, 3, 2, 4, 5, 5, 4, 5, 5, 5, 4, 5, 5, 5, 3, 4, 5, 4, 5, 4, 5, 4, 5, 5, 4, 5, 5, 5, 5, 4),
('Chong', 'Chin Ai', 43, 2, 2, 2, 1, 2, 6, 5, 3, 1, 3, 5, 3, 3, 4, 4, 5, 5, 5, 2, 1, 5, 1, 5, 4, 5, 4, 5, 4, 4, 5, 3, 4, 5, 4, 4, 5, 2, 3, 4, 4, 5, 4, 5, 4, 4, 3, 4, 4, 4),
('Mohd Paili', 'Mat Yusoff', 45, 1, 1, 1, 2, 1, 6, 5, 3, 1, 2, 6, 2, 2, 3, 3, 4, 4, 5, 1, 1, 3, 4, 4, 3, 4, 4, 5, 4, 4, 5, 3, 4, 4, 3, 4, 5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 4, 4),
('Nur Amalina', 'Bakri', 26, 2, 1, 1, 1, 2, 1, 4, 3, 1, 2, 6, 3, 4, 3, 5, 4, 5, 4, 2, 2, 1, 1, 5, 4, 5, 3, 4, 4, 4, 4, 3, 4, 3, 4, 3, 4, 4, 4, 3, 4, 4, 3, 4, 3, 4, 5, 4, 5, 4),
('Chong Ai', 'Ning', 36, 2, 2, 2, 3, 2, 4, 6, 3, 2, 3, 3, 4, 4, 3, 5, 4, 5, 4, 2, 2, 3, 2, 4, 3, 3, 4, 3, 3, 4, 5, 4, 4, 5, 4, 4, 5, 2, 3, 3, 3, 4, 3, 3, 3, 4, 4, 4, 5, 4),
('Nanda', 'Kasumatra', 29, 2, 5, 2, 3, 1, 1, 1, 6, 0, 2, 6, 1, 4, 3, 4, 5, 3, 5, 1, 2, 2, 2, 4, 3, 4, 5, 4, 5, 4, 5, 4, 3, 4, 4, 3, 3, 3, 4, 3, 4, 4, 3, 4, 3, 4, 4, 3, 3, 4),
('Siti Noor', 'Zakaria', 44, 2, 1, 1, 3, 1, 3, 3, 2, 1, 1, 1, 2, 3, 3, 3, 4, 4, 3, 2, 1, 1, 1, 4, 5, 4, 3, 4, 4, 4, 3, 4, 4, 4, 3, 4, 5, 4, 3, 4, 4, 3, 4, 4, 4, 5, 4, 4, 5, 4),
('Safwan', 'Azran', 26, 1, 1, 1, 1, 2, 6, 1, 3, 1, 2, 4, 2, 4, 3, 4, 3, 4, 5, 2, 1, 4, 2, 4, 5, 4, 4, 5, 4, 3, 4, 4, 3, 4, 5, 4, 5, 4, 5, 4, 5, 4, 5, 4, 4, 5, 4, 4, 5, 4),
('Shahida', 'Mohd Paili', 23, 2, 1, 1, 3, 2, 5, 3, 4, 2, 3, 1, 3, 2, 3, 4, 2, 4, 5, 1, 1, 4, 2, 3, 4, 3, 4, 4, 3, 4, 3, 3, 4, 3, 4, 3, 4, 4, 4, 4, 4, 5, 3, 4, 4, 3, 4, 5, 5, 5),
('Kevin', 'Ong', 26, 1, 5, 2, 3, 1, 2, 3, 3, 2, 1, 1, 2, 4, 3, 4, 3, 5, 4, 2, 2, 2, 2, 3, 4, 5, 3, 5, 5, 4, 5, 1, 4, 4, 5, 4, 3, 2, 4, 3, 5, 3, 5, 4, 3, 4, 5, 3, 4, 5);

-- --------------------------------------------------------

--
-- Table structure for table `uitm_keda_g4_admin`
--

CREATE TABLE IF NOT EXISTS `uitm_keda_g4_admin` (
  `PID` int(11) NOT NULL AUTO_INCREMENT,
  `ID` char(15) DEFAULT NULL,
  `Name` char(15) DEFAULT NULL,
  `POSITION` char(15) DEFAULT NULL,
  `DEPARTMENT` char(15) DEFAULT NULL,
  `PASSWORD` int(11) DEFAULT NULL,
  PRIMARY KEY (`PID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `uitm_keda_g4_admin`
--

INSERT INTO `uitm_keda_g4_admin` (`PID`, `ID`, `Name`, `POSITION`, `DEPARTMENT`, `PASSWORD`) VALUES
(1, '01', 'syafiq', 'superadmin', 'marketing', 1234),
(2, '02', 'lela', 'superadmin', 'facilities', 1234);

-- --------------------------------------------------------

--
-- Table structure for table `uitm_keda_g4_bemcdtable`
--

CREATE TABLE IF NOT EXISTS `uitm_keda_g4_bemcdtable` (
  `PID` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(15) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `Q1` int(11) DEFAULT NULL,
  `Q2` int(11) DEFAULT NULL,
  `Q3` int(11) DEFAULT NULL,
  `Q4` int(11) DEFAULT NULL,
  `Q5` int(11) DEFAULT NULL,
  `Q6` int(11) DEFAULT NULL,
  `Q7` int(11) DEFAULT NULL,
  `Q8` int(11) DEFAULT NULL,
  `Q9` int(11) DEFAULT NULL,
  `Q10` int(11) DEFAULT NULL,
  `Q11` int(11) DEFAULT NULL,
  `Q12` int(11) DEFAULT NULL,
  `Q13` int(11) DEFAULT NULL,
  `Q14` int(11) DEFAULT NULL,
  `Q15` int(11) DEFAULT NULL,
  `Q16` int(11) DEFAULT NULL,
  `Q17` int(11) DEFAULT NULL,
  `Q18` int(11) DEFAULT NULL,
  `Q19` int(11) DEFAULT NULL,
  `Q20` int(11) DEFAULT NULL,
  `Q21` int(11) DEFAULT NULL,
  `Q22` int(11) DEFAULT NULL,
  `Q23` int(11) DEFAULT NULL,
  `Q24` int(11) DEFAULT NULL,
  `Q25` int(11) DEFAULT NULL,
  `Q26` int(11) DEFAULT NULL,
  PRIMARY KEY (`PID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `uitm_keda_g4_bemcdtable`
--

INSERT INTO `uitm_keda_g4_bemcdtable` (`PID`, `name`, `phone`, `Q1`, `Q2`, `Q3`, `Q4`, `Q5`, `Q6`, `Q7`, `Q8`, `Q9`, `Q10`, `Q11`, `Q12`, `Q13`, `Q14`, `Q15`, `Q16`, `Q17`, `Q18`, `Q19`, `Q20`, `Q21`, `Q22`, `Q23`, `Q24`, `Q25`, `Q26`) VALUES
(6, 'Aman', 100, 1, 2, 3, 4, 5, 4, 3, 2, 1, 2, 3, 4, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3, 4, 3),
(7, 'Raman', 200, 1, 3, 2, 2, 3, 2, 2, 3, 2, 3, 3, 4, 4, 3, 3, 4, 3, 3, 4, 3, 4, 3, 4, 3, 4, 3),
(8, 'Fred', 300, 5, 5, 5, 5, 5, 5, 5, 4, 4, 4, 4, 5, 4, 5, 4, 5, 4, 5, 5, 5, 5, 5, 4, 4, 4, 4),
(9, 'OHer', 400, 2, 3, 4, 4, 4, 3, 4, 4, 3, 3, 3, 3, 4, 3, 3, 3, 3, 3, 3, 3, 4, 3, 4, 3, 4, 3),
(10, 'Poosnak', 500, 4, 4, 3, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 2),
(11, 'Yutaa', 600, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 1),
(12, 'Umar', 700, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 3, 3, 4, 3, 3, 3, 3, 3),
(13, 'Hamdan', 800, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2),
(14, 'Gary', 900, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 3, 2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `uitm_keda_g5_customer_contact`
--

CREATE TABLE IF NOT EXISTS `uitm_keda_g5_customer_contact` (
  `PID` int(11) NOT NULL AUTO_INCREMENT,
  `Q1` char(1) DEFAULT NULL,
  `Q2` char(1) DEFAULT NULL,
  `Q3` char(1) DEFAULT NULL,
  `Q4` char(1) DEFAULT NULL,
  `Q5` char(1) DEFAULT NULL,
  `Q6` char(1) DEFAULT NULL,
  `Q7` char(1) DEFAULT NULL,
  `Q8` char(1) DEFAULT NULL,
  `Q9` char(1) DEFAULT NULL,
  `Q10` char(1) DEFAULT NULL,
  PRIMARY KEY (`PID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `uitm_keda_g5_customer_contact`
--

INSERT INTO `uitm_keda_g5_customer_contact` (`PID`, `Q1`, `Q2`, `Q3`, `Q4`, `Q5`, `Q6`, `Q7`, `Q8`, `Q9`, `Q10`) VALUES
(1, '4', '2', '1', '2', '3', '4', '2', '4', '3', '2'),
(2, '5', '2', '1', '2', '2', '3', '4', '5', '2', '3'),
(3, '3', '2', '1', '2', '2', '4', '3', '3', '4', '2'),
(4, '4', '2', '2', '1', '2', '3', '4', '3', '4', '3'),
(5, '1', '1', '1', '1', '2', '2', '2', '2', '2', '3'),
(7, '5', '2', '1', '1', '1', '1', '2', '1', '5', '1'),
(8, '5', '2', '1', '1', '1', '1', '2', '1', '5', '1'),
(10, '2', '2', '1', '2', '5', '2', '2', '1', '1', '2'),
(11, '1', '1', '2', '1', '4', '4', '4', '4', '3', '3'),
(12, '1', '1', '2', '1', '4', '4', '4', '4', '3', '3'),
(13, '1', '1', '2', '1', '4', '4', '4', '4', '3', '3'),
(14, '5', '2', '2', '1', '1', '5', '4', '2', '3', '1'),
(17, '5', '2', '1', '2', '3', '2', '5', '5', '1', '1');

-- --------------------------------------------------------

--
-- Table structure for table `uitm_keda_g5_online_store`
--

CREATE TABLE IF NOT EXISTS `uitm_keda_g5_online_store` (
  `PID` int(11) NOT NULL AUTO_INCREMENT,
  `Q1` char(1) DEFAULT NULL,
  `Q2` char(1) DEFAULT NULL,
  `Q3` char(1) DEFAULT NULL,
  `Q4` char(1) DEFAULT NULL,
  `Q5` char(1) DEFAULT NULL,
  `Q6` char(1) DEFAULT NULL,
  `Q7` char(1) DEFAULT NULL,
  `Q8` char(1) DEFAULT NULL,
  `Q9` char(1) DEFAULT NULL,
  `Q10` char(1) DEFAULT NULL,
  `Q11` char(1) DEFAULT NULL,
  `Q12` char(1) DEFAULT NULL,
  PRIMARY KEY (`PID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `uitm_keda_g5_online_store`
--

INSERT INTO `uitm_keda_g5_online_store` (`PID`, `Q1`, `Q2`, `Q3`, `Q4`, `Q5`, `Q6`, `Q7`, `Q8`, `Q9`, `Q10`, `Q11`, `Q12`) VALUES
(1, '2', 'F', 'N', '2', '1', 'L', 'N', 'Y', 'Y', 'N', 'Y', 'S'),
(3, '1', 'F', 'N', '3', '2', 'O', 'Y', 'N', 'N', 'N', 'N', 'U'),
(4, '4', 'F', 'Y', '1', '1', 'L', 'N', 'Y', 'N', 'Y', 'Y', 'U'),
(7, '4', 'M', 'N', '2', '2', 'L', 'Y', 'N', 'N', 'Y', 'Y', 'S');

-- --------------------------------------------------------

--
-- Table structure for table `uitm_keda_g5_satisfy`
--

CREATE TABLE IF NOT EXISTS `uitm_keda_g5_satisfy` (
  `PID` int(11) NOT NULL AUTO_INCREMENT,
  `Q1` char(1) DEFAULT NULL,
  `Q2` char(1) DEFAULT NULL,
  `Q3` char(1) DEFAULT NULL,
  `Q4` char(1) DEFAULT NULL,
  `Q5` char(1) DEFAULT NULL,
  `Q6` char(1) DEFAULT NULL,
  `Q7` char(1) DEFAULT NULL,
  `Q8` char(1) DEFAULT NULL,
  `Q9` char(1) DEFAULT NULL,
  `Q10` char(1) DEFAULT NULL,
  `Q11` char(1) DEFAULT NULL,
  `Q12` char(1) DEFAULT NULL,
  `Q13` char(1) DEFAULT NULL,
  `Q14` char(1) DEFAULT NULL,
  PRIMARY KEY (`PID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `uitm_keda_g5_satisfy`
--

INSERT INTO `uitm_keda_g5_satisfy` (`PID`, `Q1`, `Q2`, `Q3`, `Q4`, `Q5`, `Q6`, `Q7`, `Q8`, `Q9`, `Q10`, `Q11`, `Q12`, `Q13`, `Q14`) VALUES
(1, 'Y', 'N', 'N', 'Y', 'N', 'N', 'Y', 'N', 'Y', 'Y', 'N', 'N', 'Y', 'Y'),
(3, 'N', 'Y', 'N', 'N', 'N', 'Y', 'N', 'N', 'Y', 'N', 'Y', 'N', 'N', 'Y'),
(4, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y'),
(6, 'Y', 'N', 'N', 'Y', 'Y', 'N', 'N', 'N', 'N', 'N', 'Y', 'Y', 'N', 'N'),
(8, 'Y', 'Y', 'N', 'Y', 'N', 'N', 'Y', 'N', 'Y', 'Y', 'N', 'Y', 'Y', 'Y'),
(9, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y'),
(10, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y'),
(11, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `uitm_keda_g5_staff`
--

CREATE TABLE IF NOT EXISTS `uitm_keda_g5_staff` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) DEFAULT NULL,
  `DEPARTMENT` varchar(20) DEFAULT NULL,
  `USERNAME` varchar(15) DEFAULT NULL,
  `PASSWORD` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `uitm_keda_g5_staff`
--

INSERT INTO `uitm_keda_g5_staff` (`ID`, `NAME`, `DEPARTMENT`, `USERNAME`, `PASSWORD`) VALUES
(1, 'Syahril Niezam', 'quality control', 'syahril', 'qwerty'),
(2, 'noor nasrul md noor', 'human resource', 'nasrul', 'qwerty'),
(3, 'maizatul hidayah', 'customer service', 'mai', 'qwerty'),
(4, 'abdul latif', 'admin', 'abdlatif', 'qwerty'),
(18, 'asd', 'admin', 'qwerty', 'qwerty'),
(19, 'asd', 'admin', 'qwerty', 'qwerty'),
(20, 'abdrahim', 'admin', 'abdrahim', 'qwerty');

-- --------------------------------------------------------

--
-- Table structure for table `university`
--

CREATE TABLE IF NOT EXISTS `university` (
  `uni_id` int(11) NOT NULL AUTO_INCREMENT,
  `uni_accesskey` varchar(45) DEFAULT '12345',
  `uni_password` varchar(45) DEFAULT '12345',
  `uni_shortname` varchar(45) DEFAULT NULL,
  `uni_fullname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`uni_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `university`
--

INSERT INTO `university` (`uni_id`, `uni_accesskey`, `uni_password`, `uni_shortname`, `uni_fullname`) VALUES
(1, '12345', '12345', 'UiTM', 'Universiti Teknologi Mara'),
(2, '12345', '12345', 'UUM', 'Universiti Utara Malaysia');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
