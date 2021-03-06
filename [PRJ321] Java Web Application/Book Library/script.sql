USE [master]
GO
/****** Object:  Database [Asg_PRJ321]    Script Date: 7/15/2020 3:56:05 PM ******/
CREATE DATABASE [Asg_PRJ321]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Asg_PRJ321', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS1\MSSQL\DATA\Asg_PRJ321.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Asg_PRJ321_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS1\MSSQL\DATA\Asg_PRJ321_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Asg_PRJ321] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Asg_PRJ321].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Asg_PRJ321] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET ARITHABORT OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Asg_PRJ321] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Asg_PRJ321] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Asg_PRJ321] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Asg_PRJ321] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Asg_PRJ321] SET  MULTI_USER 
GO
ALTER DATABASE [Asg_PRJ321] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Asg_PRJ321] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Asg_PRJ321] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Asg_PRJ321] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [Asg_PRJ321] SET DELAYED_DURABILITY = DISABLED 
GO
USE [Asg_PRJ321]
GO
/****** Object:  Table [dbo].[tblBooks]    Script Date: 7/15/2020 3:56:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblBooks](
	[bookID] [nvarchar](50) NOT NULL,
	[bookName] [nvarchar](50) NOT NULL,
	[price] [int] NOT NULL,
	[author] [nvarchar](50) NOT NULL,
	[datePublic] [date] NOT NULL,
	[quantity] [int] NOT NULL,
	[linkImage] [nvarchar](500) NULL,
 CONSTRAINT [PK_tblBooks] PRIMARY KEY CLUSTERED 
(
	[bookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 7/15/2020 3:56:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrder](
	[orderID] [nvarchar](50) NOT NULL,
	[rentDate] [date] NULL,
	[returnDate] [date] NULL,
	[userID] [nvarchar](50) NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_tblOrder] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 7/15/2020 3:56:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetail](
	[bookID] [nvarchar](50) NOT NULL,
	[orderID] [nvarchar](50) NULL,
	[amount] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblRole]    Script Date: 7/15/2020 3:56:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRole](
	[roleID] [nvarchar](50) NOT NULL,
	[roleName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tblRole] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 7/15/2020 3:56:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUsers](
	[userName] [nvarchar](50) NOT NULL,
	[fullName] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[roleID] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tblUsers] PRIMARY KEY CLUSTERED 
(
	[userName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[tblBooks] ([bookID], [bookName], [price], [author], [datePublic], [quantity], [linkImage]) VALUES (N'CSD201', N'Data structures and algorithms', 45, N'Adam Drozdek', CAST(N'2008-05-05' AS Date), 135, N'CSD201.jpg')
INSERT [dbo].[tblBooks] ([bookID], [bookName], [price], [author], [datePublic], [quantity], [linkImage]) VALUES (N'CSI101', N'Introduction to Computing', 65, N'Greg Anderson, David Ferro, Robert Hilton', CAST(N'2005-04-24' AS Date), 120, N'CSI101.jpg')
INSERT [dbo].[tblBooks] ([bookID], [bookName], [price], [author], [datePublic], [quantity], [linkImage]) VALUES (N'DBI201', N'Introduction to Databases', 40, N'Jeffrey D.Ullman, Jennifer Widom', CAST(N'2008-12-12' AS Date), 90, N'DBI201.jpg')
INSERT [dbo].[tblBooks] ([bookID], [bookName], [price], [author], [datePublic], [quantity], [linkImage]) VALUES (N'ETR402', N'Software Entrepreneurship', 50, N'Jeffry A. Timmons and Stephen Spinelli', CAST(N'2004-01-12' AS Date), 110, N'ETR402.jpg')
INSERT [dbo].[tblBooks] ([bookID], [bookName], [price], [author], [datePublic], [quantity], [linkImage]) VALUES (N'HCI201', N'Human Computer Interation', 44, N'Helen Sharp, Yvonne Rogers, Jenny Preece', CAST(N'2007-12-12' AS Date), 90, N'HCI201.jpg')
INSERT [dbo].[tblBooks] ([bookID], [bookName], [price], [author], [datePublic], [quantity], [linkImage]) VALUES (N'ITE302', N'Ethics in Information Technology', 34, N'George W. Reynolds', CAST(N'2010-05-15' AS Date), 80, N'ITE302.jpg')
INSERT [dbo].[tblBooks] ([bookID], [bookName], [price], [author], [datePublic], [quantity], [linkImage]) VALUES (N'JDP101', N'Elementary Japanese 1', 35, N'AOTS', CAST(N'2007-01-01' AS Date), 150, N'JDP101.jpg')
INSERT [dbo].[tblBooks] ([bookID], [bookName], [price], [author], [datePublic], [quantity], [linkImage]) VALUES (N'JDP121', N'Elementary Japaneses 2', 35, N'AOTS', CAST(N'2007-01-01' AS Date), 150, N'JDP121.jpg')
INSERT [dbo].[tblBooks] ([bookID], [bookName], [price], [author], [datePublic], [quantity], [linkImage]) VALUES (N'JDP131', N'Elementary Japaneses 3', 53, N'AOTS', CAST(N'2007-01-01' AS Date), 150, N'JDP131.jpg')
INSERT [dbo].[tblBooks] ([bookID], [bookName], [price], [author], [datePublic], [quantity], [linkImage]) VALUES (N'MAD121', N'Discrete Mathematics', 30, N'Kenneth H. Rosen', CAST(N'2007-08-11' AS Date), 120, N'MAD121.jpg')
INSERT [dbo].[tblBooks] ([bookID], [bookName], [price], [author], [datePublic], [quantity], [linkImage]) VALUES (N'NWC201', N'Computer Networking', 40, N'James F. Kurose, Keith W. Ross', CAST(N'2008-12-01' AS Date), 150, N'NWC201.jpg')
INSERT [dbo].[tblBooks] ([bookID], [bookName], [price], [author], [datePublic], [quantity], [linkImage]) VALUES (N'OSG201', N'Operating System', 40, N'Andrew S. Tanenbaum', CAST(N'2001-07-08' AS Date), 50, N'OSG201.jpg')
INSERT [dbo].[tblBooks] ([bookID], [bookName], [price], [author], [datePublic], [quantity], [linkImage]) VALUES (N'PRJ191', N'Programming Fundermentals Using C', 50, N'Evan, Weaver', CAST(N'2007-01-08' AS Date), 100, N'PRJ191.jpg')
INSERT [dbo].[tblBooks] ([bookID], [bookName], [price], [author], [datePublic], [quantity], [linkImage]) VALUES (N'SSG102', N'Working in Group', 34, N'Isa N. Engleberg, Diana R. Wynn.', CAST(N'2010-05-05' AS Date), 100, N'SSG102.jpg')
INSERT [dbo].[tblBooks] ([bookID], [bookName], [price], [author], [datePublic], [quantity], [linkImage]) VALUES (N'SWD391', N'Software Architecture and Design', 43, N' Kai Qian, Xiang Fu, Lixin Tao', CAST(N'2010-06-07' AS Date), 125, N'SWD391.jpg')
INSERT [dbo].[tblBooks] ([bookID], [bookName], [price], [author], [datePublic], [quantity], [linkImage]) VALUES (N'SWE101', N'Introduction to Software Engineering', 64, N'Ian Sommerville', CAST(N'2007-12-12' AS Date), 140, N'SWE101.jpg')
INSERT [dbo].[tblOrder] ([orderID], [rentDate], [returnDate], [userID], [status]) VALUES (N'2020711-144514', CAST(N'2020-07-11' AS Date), CAST(N'2020-07-31' AS Date), N'SE140001', 1)
INSERT [dbo].[tblOrder] ([orderID], [rentDate], [returnDate], [userID], [status]) VALUES (N'2020711-14593', CAST(N'2020-07-11' AS Date), CAST(N'2020-08-01' AS Date), N'SE140001', 1)
INSERT [dbo].[tblOrder] ([orderID], [rentDate], [returnDate], [userID], [status]) VALUES (N'2020712-121938', CAST(N'2020-07-12' AS Date), CAST(N'2020-08-04' AS Date), N'SE140001', 1)
INSERT [dbo].[tblOrder] ([orderID], [rentDate], [returnDate], [userID], [status]) VALUES (N'2020712-192310', CAST(N'2020-07-12' AS Date), CAST(N'2020-08-02' AS Date), N'SE140001', 1)
INSERT [dbo].[tblOrder] ([orderID], [rentDate], [returnDate], [userID], [status]) VALUES (N'2020714-151720', CAST(N'2020-07-15' AS Date), CAST(N'2020-07-30' AS Date), N'SE140001', 1)
INSERT [dbo].[tblOrder] ([orderID], [rentDate], [returnDate], [userID], [status]) VALUES (N'2020714-152758', CAST(N'2020-07-14' AS Date), CAST(N'2020-07-26' AS Date), N'SE131313', 1)
INSERT [dbo].[tblOrder] ([orderID], [rentDate], [returnDate], [userID], [status]) VALUES (N'2020715-112220', CAST(N'2020-07-15' AS Date), CAST(N'2020-07-31' AS Date), N'SE131313', 1)
INSERT [dbo].[tblOrder] ([orderID], [rentDate], [returnDate], [userID], [status]) VALUES (N'202079-151538', CAST(N'2020-07-10' AS Date), CAST(N'2020-07-24' AS Date), N'SE140001', 1)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'CSI101', N'202079-151538', 10)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'ETR402', N'202079-151538', 10)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'JDP101', N'2020711-144514', 30)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'JDP121', N'2020711-144514', 20)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'JDP131', N'2020711-144514', 15)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'SWE101', N'2020711-14593', 12)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'SWD391', N'2020711-14593', 10)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'MAD121', N'2020711-14593', 8)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'CSD201', N'2020712-121938', 12)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'CSI101', N'2020712-121938', 10)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'DBI201', N'2020712-121938', 11)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'PRJ191', N'2020712-192310', 24)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'ITE302', N'2020712-192310', 10)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'OSG201', N'2020712-192310', 16)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'CSD201', N'2020714-151720', 10)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'CSI101', N'2020714-151720', 20)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'CSD201', N'2020714-152758', 10)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'CSI101', N'2020715-112220', 70)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'SSG102', N'2020711-14593', 5)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'CSI101', N'2020714-152758', 10)
INSERT [dbo].[tblOrderDetail] ([bookID], [orderID], [amount]) VALUES (N'CSD201', N'202079-151538', 10)
INSERT [dbo].[tblRole] ([roleID], [roleName]) VALUES (N'RL001', N'Admin')
INSERT [dbo].[tblRole] ([roleID], [roleName]) VALUES (N'RL002', N'User')
INSERT [dbo].[tblUsers] ([userName], [fullName], [password], [roleID]) VALUES (N'SE131313', N'Nguyen Van Son', N'12345', N'RL002')
INSERT [dbo].[tblUsers] ([userName], [fullName], [password], [roleID]) VALUES (N'SE140001', N'Thi Ly Vu', N'12345678', N'RL002')
INSERT [dbo].[tblUsers] ([userName], [fullName], [password], [roleID]) VALUES (N'SE140996', N'Nguyen Van Hung', N'12345678', N'RL001')
INSERT [dbo].[tblUsers] ([userName], [fullName], [password], [roleID]) VALUES (N'SE141111', N'Nguyen Thi Quy', N'123456', N'RL002')
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_PersonOrder1] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userName])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_PersonOrder1]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK__tblOrderD__bookI__398D8EEE] FOREIGN KEY([bookID])
REFERENCES [dbo].[tblBooks] ([bookID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK__tblOrderD__bookI__398D8EEE]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK__tblOrderD__order__37A5467C] FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrder] ([orderID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK__tblOrderD__order__37A5467C]
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD  CONSTRAINT [FK_PersonOrder] FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRole] ([roleID])
GO
ALTER TABLE [dbo].[tblUsers] CHECK CONSTRAINT [FK_PersonOrder]
GO
USE [master]
GO
ALTER DATABASE [Asg_PRJ321] SET  READ_WRITE 
GO
