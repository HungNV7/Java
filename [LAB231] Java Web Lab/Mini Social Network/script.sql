USE [master]
GO
/****** Object:  Database [Mini Social Network]    Script Date: 9/30/2020 5:02:29 PM ******/
CREATE DATABASE [Mini Social Network]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Mini Social Network', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\Mini Social Network.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Mini Social Network_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\Mini Social Network_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Mini Social Network] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Mini Social Network].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Mini Social Network] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Mini Social Network] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Mini Social Network] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Mini Social Network] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Mini Social Network] SET ARITHABORT OFF 
GO
ALTER DATABASE [Mini Social Network] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Mini Social Network] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Mini Social Network] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Mini Social Network] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Mini Social Network] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Mini Social Network] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Mini Social Network] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Mini Social Network] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Mini Social Network] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Mini Social Network] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Mini Social Network] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Mini Social Network] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Mini Social Network] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Mini Social Network] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Mini Social Network] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Mini Social Network] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Mini Social Network] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Mini Social Network] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Mini Social Network] SET  MULTI_USER 
GO
ALTER DATABASE [Mini Social Network] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Mini Social Network] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Mini Social Network] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Mini Social Network] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [Mini Social Network] SET DELAYED_DURABILITY = DISABLED 
GO
USE [Mini Social Network]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 9/30/2020 5:02:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[email] [nvarchar](50) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[password] [nvarchar](100) NOT NULL,
	[role] [nvarchar](50) NOT NULL,
	[status] [nvarchar](50) NOT NULL,
	[code] [nvarchar](50) NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Article]    Script Date: 9/30/2020 5:02:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Article](
	[articleID] [nvarchar](50) NOT NULL,
	[title] [nvarchar](50) NULL,
	[description] [nvarchar](max) NULL,
	[image] [nvarchar](100) NULL,
	[date] [date] NOT NULL,
	[status] [nvarchar](50) NOT NULL,
	[email] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Article] PRIMARY KEY CLUSTERED 
(
	[articleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Comment]    Script Date: 9/30/2020 5:02:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comment](
	[commentID] [nvarchar](50) NOT NULL,
	[articleID] [nvarchar](50) NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[detail] [nvarchar](500) NOT NULL,
	[date] [date] NOT NULL,
	[status] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Comment] PRIMARY KEY CLUSTERED 
(
	[commentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Emotion]    Script Date: 9/30/2020 5:02:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Emotion](
	[emotionID] [nvarchar](50) NOT NULL,
	[articleID] [nvarchar](50) NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[date] [date] NOT NULL,
	[icon] [nchar](10) NOT NULL,
 CONSTRAINT [PK_Emotion] PRIMARY KEY CLUSTERED 
(
	[emotionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Notification]    Script Date: 9/30/2020 5:02:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Notification](
	[notifyID] [nvarchar](50) NOT NULL,
	[articleID] [nvarchar](50) NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[date] [date] NOT NULL,
	[type] [nvarchar](50) NOT NULL,
	[status] [nvarchar](50) NOT NULL,
	[isRead] [bit] NOT NULL,
 CONSTRAINT [PK_Notification] PRIMARY KEY CLUSTERED 
(
	[notifyID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Status]    Script Date: 9/30/2020 5:02:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Status](
	[statusID] [nvarchar](50) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Status] PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Account] ([email], [name], [password], [role], [status], [code]) VALUES (N'binz@gmail.com', N'Binz Da Poet', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Member', N'S002', N'888999')
INSERT [dbo].[Account] ([email], [name], [password], [role], [status], [code]) VALUES (N'caytamto@gmail.com', N'giang giang giang', N'96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', N'Member', N'S002', N'777849')
INSERT [dbo].[Account] ([email], [name], [password], [role], [status], [code]) VALUES (N'dinhtrithuc2021@gmail.com', N'Đỉnh Tri Thức', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Member', N'S002', N'888999')
INSERT [dbo].[Account] ([email], [name], [password], [role], [status], [code]) VALUES (N'hoangnt@fpt.edu.vn', N'Hoang Nguyen-The', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Member', N'S002', N'888999')
INSERT [dbo].[Account] ([email], [name], [password], [role], [status], [code]) VALUES (N'hung@gmail.com', N'Hung Nguyen', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Member', N'S002', N'888999')
INSERT [dbo].[Account] ([email], [name], [password], [role], [status], [code]) VALUES (N'hungnvse140996@fpt.edu.vn', N'Nguyen Van Hung', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Member', N'S002', N'888999')
INSERT [dbo].[Account] ([email], [name], [password], [role], [status], [code]) VALUES (N'karik@gmail.com', N'Karik Nguyen', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Member', N'S002', N'888999')
INSERT [dbo].[Account] ([email], [name], [password], [role], [status], [code]) VALUES (N'ngothuhuong@gmail.com', N'Ngo Thu Huong', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Member', N'S002', N'888999')
INSERT [dbo].[Account] ([email], [name], [password], [role], [status], [code]) VALUES (N'tranthanh@gmail.com', N'Tran Thanh', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Member', N'S002', N'888999')
INSERT [dbo].[Account] ([email], [name], [password], [role], [status], [code]) VALUES (N'tunhm@fpt.edu.vn', N'Nguyen Hoang Minh Tu', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Member', N'S002', N'888999')
INSERT [dbo].[Account] ([email], [name], [password], [role], [status], [code]) VALUES (N'tunhmse140357@fpt.edu.vn', N'Minh la Tu', N'689571188535a64d291f23f493e764dcd2c56564a71062ce892ec9d28624d1e3', N'Member', N'S002', N'714785')
INSERT [dbo].[Account] ([email], [name], [password], [role], [status], [code]) VALUES (N'vuthugiang2610@gmail.com', N'Vu Thu Giang', N'96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', N'Member', N'S002', N'527313')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'117092020140700', NULL, N'Chúc mừng team Wowy với 4 tiết mục truyền tải thông điệp nhân văn ', N'119036160_3292640970802827_890299783545220698_o.jpg', CAST(N'2020-09-16' AS Date), N'S004', N'binz@gmail.com')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'117092020140900', NULL, N'Sau 1 tháng BIGCITYBOI vẫn giữ vững top 1 Spotify và Apple Music Việt Nam Chart 🎼🎼

So blessed, thank you my homies', N'117168572_3180358472031078_5291309587131647617_o.jpg', CAST(N'2020-09-16' AS Date), N'S004', N'binz@gmail.com')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'117092020141000', NULL, N'Touliver x Binz', N'104767895_3069085376491722_1385432970752594283_o.jpg', CAST(N'2020-09-15' AS Date), N'S004', N'binz@gmail.com')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'117092020141100', NULL, N'Bring the old me back.', N'117174214_4485728071452520_4054495398414436624_o.jpg', CAST(N'2020-09-13' AS Date), N'S004', N'karik@gmail.com')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'117092020141200', NULL, N'Karik , Wowy , Justatee , Binz , Rhysmastic , Touliver và ?
mong được gặp những tài năng trẻ đầy nhiệt huyết với nhạc Rap trong ct này sắp tới .', N'103980271_4247253921966604_6078033365297060932_o.jpg', CAST(N'2020-09-16' AS Date), N'S004', N'karik@gmail.com')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'117092020141500', NULL, N'Mọi người xem RAPVIET tập hôm qua chưa???? Kkkkk', N'117770233_3826528517376441_7929485717123455575_o.jpg', CAST(N'2020-09-11' AS Date), N'S004', N'tranthanh@gmail.com')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'117092020141700', NULL, N'Trước mỗi cuộc gọi, ai cũng nghe tổng đài nhắc; tin nhắn cũng nhắc; facebook - zalo - viber đều nhắc... Ai chưa cài đặt, thực hiện ngay điều cần thiết này nha cả nhà!

Cài đặt Ứng dụng Khẩu trang điện tử Bluezone - Bảo vệ bản thân và cùng cài đặt cho 3 người khác để bảo vệ cộng đồng. Thiết thực & tích cực.', N'118017254_3826458244050135_6029157036226819478_o.jpg', CAST(N'2020-09-12' AS Date), N'S004', N'tranthanh@gmail.com')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'117092020142100', N'[THE STORY OF US]', N'Các bạn ơi! Các bạn có thích nghe kể chuyện không nào? Nếu có thì hãy cùng lắng nghe Câu chuyện của chúng tớ, về đại gia đình Đỉnh Tri Thức và hành trình 5 tháng hoạt động cùng với mọi người nhé :3
💛 Đỉnh Tri Thức không chỉ là là một chương trình học thuật, mà đó còn là một câu chuyện. Và mỗi người chúng ta, là một phần của câu chuyện...', N'40444286_2328556450518448_9134515730505531392_o.jpg', CAST(N'2020-09-17' AS Date), N'S004', N'dinhtrithuc2021@gmail.com')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'117092020142300', NULL, N'Mai là ngày rùa chính thức hòa mình vào đại dương, sau quá trình gần 6 tháng chật vật tập bơi.
Cũng có nhiều lần chơi vơi lắm, nhưng đã rất mạnh mẽ tới được biển rồi, chỉ còn chờ ít giờ nữa để tỏa sáng thôi...', N'50984063_2029076167208551_9171679683085860864_o.jpg', CAST(N'2020-09-13' AS Date), N'S004', N'ngothuhuong@gmail.com')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'117092020142400', NULL, N'Rồi không lâu nữa, các em sẽ phải bước ra biển lớn, dù có tiếc nuối phố núi của chúng mình tới đâu.
Thế nên, hãy tới buổi Sharing day của các anh chị ĐTT, để hành trình tập bơi sắp tới nhẹ nhàng và bớt hoang mang hơn nha ❤', N'48418335_1977397742376394_6964213393708285952_n.jpg', CAST(N'2020-09-13' AS Date), N'S004', N'ngothuhuong@gmail.com')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'117092020142500', NULL, N'Thật sự thì bây giờ cũng chẳng biết nói gì nữa, vì bao nhiêu lời xúc động thì mọi người đã nói hộ vào tối qua rồi...
ĐTT với mình, không còn là thích, là yêu nữa mà đã thành thương rồi. Mình không biết người yêu cũ tròn méo ra sao, day dứt thế nào mà chắc là cũng chỉ dai như bạn này thôi, bạn làm mình cảm nắng từ 2/2/2016, tới hè thì tìm hiểu rồi thương nhau từ đó tới tận bây giờ. Mình tuổi trẻ nông nổi, tính tình thất thường, hay lấy bạn ra để thử nghiệm mấy trò điên điên mà bạn vẫn khoan dung vui vẻ chấp nhận hết. Thôi nói chung là thương nè, thương nhiều lắm ❤
Ngày bạn khó khăn, có mình bên cạnh. Đoạn đường thanh xuân, mình cùng sánh đôi ❤', N'27912490_1573618149421024_2286239404091531470_o.jpg', CAST(N'2020-09-12' AS Date), N'S004', N'ngothuhuong@gmail.com')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'117092020142700', N'"Đỉnh Tri Thức - Tiếp Sức Chống Dịch"', N'', N'116878971_1253854004958436_4625279855837360746_o.jpg', CAST(N'2020-09-13' AS Date), N'S004', N'hungnvse140996@fpt.edu.vn')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'117092020142800', NULL, N'', N'106565052_1223711721305998_1603728961808607821_o (1).jpg', CAST(N'2020-09-14' AS Date), N'S004', N'hungnvse140996@fpt.edu.vn')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'12509094000', N'[HÌNH NHƯ ĐTT VỪA ĐÁNH RƠI ĐIỀU GÌ ĐÓ?]', N'-------
“Chủ nhật ơi sao mà ngắn thế,
Vừa chớp mắt đã đến thứ Hai
Đầu tuần công việc lai rai
Cuối tuần mới hoảng đét-lai hết hồn”', N'119470814_4554002837973787_4025403306921437571_o.png', CAST(N'2020-09-25' AS Date), N'S004', N'hungnvse140996@fpt.edu.vn')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'125092020112900', N'[BẢN ĐỒ THẦN KÌ]', N'-------
🤲🏻 Đến hẹn lại lên, hôm nay Đỉnh Tri Thức sẽ đưa bạn đến với một chủ đề không quen không lạ, không lạ mà quen, không quen mà lạ, nhưng cũng vừa lạ vừa quen rồi đây, đó là: Những địa điểm “nóng hổi vừa thổi vừa check-in” tại trường THPT Chuyên Nguyễn Du của chúng ta. Nếu như trong truyện Harry Potter có bản đồ đạo tặc nổi tiếng thì chúng mình cũng nên có một chiếc "Bản Đồ Thần Kỳ" chỉ dẫn mọi ngóc ngách trong trường chứ nhỉ, đặc biệt lại càng hữu ích đối với các tân ND-ers đó nha.', N'118231403_4458499444190794_6166629778022484235_o.jpg', CAST(N'2020-09-26' AS Date), N'S004', N'hungnvse140996@fpt.edu.vn')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'12592020105727', N'Báº¡n giÃ  tui nÃ¨ mn Æ¡i :)))', N'', N'', CAST(N'2020-09-25' AS Date), N'S003', N'hungnvse140996@fpt.edu.vn')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'12892020103225', N'', N'CHo TU
Ahii do ngoc', N'', CAST(N'2020-09-28' AS Date), N'S003', N'hungnvse140996@fpt.edu.vn')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'12892020105535', N'HAPPY BIRTHDAY 6 YEARS DTT', N'', N'119983758_4606884189352318_1803558427184268564_o.jpg', CAST(N'2020-09-28' AS Date), N'S003', N'hungnvse140996@fpt.edu.vn')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'1299202020220', N'Congratulation for DTT ', N'', N'119983758_4606884189352318_1803558427184268564_o.jpg', CAST(N'2020-09-29' AS Date), N'S003', N'hungnvse140996@fpt.edu.vn')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'13092020111126', N'This is LIKE button', N'', N'like.jpg', CAST(N'2020-09-30' AS Date), N'S003', N'tunhmse140357@fpt.edu.vn')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'1309202011134', N'This is another LIKE button', N'', N'like-color.jpg', CAST(N'2020-09-30' AS Date), N'S003', N'tunhmse140357@fpt.edu.vn')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'13092020112910', N'This is a DISLIKE button', N'', N'unlike.jpg', CAST(N'2020-09-30' AS Date), N'S003', N'hungnvse140996@fpt.edu.vn')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'13092020155846', N'this is some ', N'hahahahahahahahahehehehehehkjdZNXVkzsvk;ldnflvkjnfdkjv', N'', CAST(N'2020-09-30' AS Date), N'S003', N'hungnvse140996@fpt.edu.vn')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'1309202016249', N'this is me', N'ke i become crazy now, ...', N'104767895_3069085376491722_1385432970752594283_o.jpg', CAST(N'2020-09-30' AS Date), N'S003', N'vuthugiang2610@gmail.com')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'13092020165412', N'This is another LIKE button :))))', N'This is a blue like button', N'like-color.jpg', CAST(N'2020-09-30' AS Date), N'S003', N'hungnvse140996@fpt.edu.vn')
INSERT [dbo].[Article] ([articleID], [title], [description], [image], [date], [status], [email]) VALUES (N'1309202017057', N'Tui thich mot nguoi ten bat dau bang chu H', N'', N'119983758_4606884189352318_1803558427184268564_o.jpg', CAST(N'2020-09-30' AS Date), N'S003', N'hungnvse140996@fpt.edu.vn')
INSERT [dbo].[Comment] ([commentID], [articleID], [email], [detail], [date], [status]) VALUES (N'2299202016238', N'117092020142100', N'hungnvse140996@fpt.edu.vn', N'I love DTT 3000 <3', CAST(N'2020-09-29' AS Date), N'S006')
INSERT [dbo].[Comment] ([commentID], [articleID], [email], [detail], [date], [status]) VALUES (N'22992020163350', N'117092020140700', N'hungnvse140996@fpt.edu.vn', N'A biz dep zai qua troi <3', CAST(N'2020-09-29' AS Date), N'S005')
INSERT [dbo].[Comment] ([commentID], [articleID], [email], [detail], [date], [status]) VALUES (N'22992020201834', N'117092020142100', N'hungnvse140996@fpt.edu.vn', N'wow! such a nice picture <3', CAST(N'2020-09-29' AS Date), N'S005')
INSERT [dbo].[Comment] ([commentID], [articleID], [email], [detail], [date], [status]) VALUES (N'22992020201913', N'12509094000', N'hungnvse140996@fpt.edu.vn', N'I just want to click love :3', CAST(N'2020-09-29' AS Date), N'S005')
INSERT [dbo].[Comment] ([commentID], [articleID], [email], [detail], [date], [status]) VALUES (N'22992020202022', N'125092020112900', N'hungnvse140996@fpt.edu.vn', N'what is in 1 area?', CAST(N'2020-09-29' AS Date), N'S005')
INSERT [dbo].[Comment] ([commentID], [articleID], [email], [detail], [date], [status]) VALUES (N'22992020202040', N'117092020140900', N'hungnvse140996@fpt.edu.vn', N'Congratulation Binz bro :<', CAST(N'2020-09-29' AS Date), N'S005')
INSERT [dbo].[Comment] ([commentID], [articleID], [email], [detail], [date], [status]) VALUES (N'22992020202623', N'1299202020220', N'hungnvse140996@fpt.edu.vn', N'Con choa nay :D', CAST(N'2020-09-29' AS Date), N'S006')
INSERT [dbo].[Comment] ([commentID], [articleID], [email], [detail], [date], [status]) VALUES (N'22992020202641', N'1299202020220', N'binz@gmail.com', N'Hello Hung. I''m Biz', CAST(N'2020-09-29' AS Date), N'S005')
INSERT [dbo].[Comment] ([commentID], [articleID], [email], [detail], [date], [status]) VALUES (N'22992020204552', N'125092020112900', N'karik@gmail.com', N'wow! it so amazing!!!!!', CAST(N'2020-09-29' AS Date), N'S005')
INSERT [dbo].[Comment] ([commentID], [articleID], [email], [detail], [date], [status]) VALUES (N'23092020112258', N'125092020112900', N'binz@gmail.com', N'I love you 3000 <3', CAST(N'2020-09-30' AS Date), N'S006')
INSERT [dbo].[Comment] ([commentID], [articleID], [email], [detail], [date], [status]) VALUES (N'23092020112939', N'13092020112910', N'binz@gmail.com', N'Hello Hung', CAST(N'2020-09-30' AS Date), N'S005')
INSERT [dbo].[Comment] ([commentID], [articleID], [email], [detail], [date], [status]) VALUES (N'2309202016531', N'1309202016249', N'caytamto@gmail.com', N'hello', CAST(N'2020-09-30' AS Date), N'S006')
INSERT [dbo].[Comment] ([commentID], [articleID], [email], [detail], [date], [status]) VALUES (N'23092020165431', N'13092020165412', N'hungnvse140996@fpt.edu.vn', N'This is .........', CAST(N'2020-09-30' AS Date), N'S006')
INSERT [dbo].[Comment] ([commentID], [articleID], [email], [detail], [date], [status]) VALUES (N'23092020165445', N'13092020165412', N'binz@gmail.com', N'Con choa nay', CAST(N'2020-09-30' AS Date), N'S005')
INSERT [dbo].[Comment] ([commentID], [articleID], [email], [detail], [date], [status]) VALUES (N'23092020165532', N'13092020165412', N'binz@gmail.com', N'I love you 3000 <3', CAST(N'2020-09-30' AS Date), N'S005')
INSERT [dbo].[Comment] ([commentID], [articleID], [email], [detail], [date], [status]) VALUES (N'2309202016618', N'1309202016249', N'vuthugiang2610@gmail.com', N'hhh', CAST(N'2020-09-30' AS Date), N'S006')
INSERT [dbo].[Comment] ([commentID], [articleID], [email], [detail], [date], [status]) VALUES (N'230920201679', N'13092020155846', N'caytamto@gmail.com', N'I love you 3000 <3', CAST(N'2020-09-30' AS Date), N'S005')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'3299202016140', N'125092020112900', N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'3299202016153', N'12509094000', N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'3299202016158', N'117092020142100', N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'3299202016218', N'117092020140700', N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'32992020163332', N'117092020140900', N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'32992020163555', N'117092020142700', N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'3299202019451', N'117092020140700', N'binz@gmail.com', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'32992020201516', N'117092020142500', N'binz@gmail.com', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'32992020201525', N'117092020142800', N'binz@gmail.com', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'3299202020154', N'125092020112900', N'binz@gmail.com', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'32992020201554', N'125092020112900', N'dinhtrithuc2021@gmail.com', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'32992020201556', N'12509094000', N'dinhtrithuc2021@gmail.com', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'32992020201559', N'117092020142100', N'dinhtrithuc2021@gmail.com', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'3299202020158', N'12509094000', N'binz@gmail.com', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'32992020201612', N'117092020141200', N'dinhtrithuc2021@gmail.com', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'32992020201621', N'117092020141000', N'dinhtrithuc2021@gmail.com', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'32992020201624', N'117092020142800', N'dinhtrithuc2021@gmail.com', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'32992020201628', N'117092020141100', N'dinhtrithuc2021@gmail.com', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'3299202020163', N'117092020140900', N'dinhtrithuc2021@gmail.com', CAST(N'2020-09-29' AS Date), N'Dislike   ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'32992020201650', N'125092020112900', N'karik@gmail.com', CAST(N'2020-09-29' AS Date), N'Dislike   ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'32992020201653', N'12509094000', N'karik@gmail.com', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'32992020201656', N'117092020142100', N'karik@gmail.com', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'32992020201659', N'117092020140700', N'karik@gmail.com', CAST(N'2020-09-29' AS Date), N'Dislike   ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'3299202020175', N'117092020142400', N'karik@gmail.com', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'3299202020178', N'117092020141500', N'karik@gmail.com', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'32992020204652', N'117092020142400', N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-29' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'33092020111010', N'117092020141200', N'tunhmse140357@fpt.edu.vn', CAST(N'2020-09-30' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'33092020111016', N'117092020142100', N'tunhmse140357@fpt.edu.vn', CAST(N'2020-09-30' AS Date), N'Dislike   ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'33092020111023', N'125092020112900', N'tunhmse140357@fpt.edu.vn', CAST(N'2020-09-30' AS Date), N'Like      ')
INSERT [dbo].[Emotion] ([emotionID], [articleID], [email], [date], [icon]) VALUES (N'33092020165919', N'117092020142800', N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-30' AS Date), N'Like      ')
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'3299202016238', N'117092020142100', N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-29' AS Date), N'Comment', N'S008', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'32992020163350', N'117092020140700', N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-29' AS Date), N'Comment', N'S007', 1)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'32992020201834', N'117092020142100', N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-29' AS Date), N'Comment', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'32992020202040', N'117092020140900', N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-29' AS Date), N'Comment', N'S007', 1)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'32992020202641', N'1299202020220', N'binz@gmail.com', CAST(N'2020-09-29' AS Date), N'Comment', N'S008', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'32992020204552', N'125092020112900', N'karik@gmail.com', CAST(N'2020-09-29' AS Date), N'Comment', N'S007', 1)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'33092020112258', N'125092020112900', N'binz@gmail.com', CAST(N'2020-09-30' AS Date), N'Comment', N'S008', 1)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'33092020112939', N'13092020112910', N'binz@gmail.com', CAST(N'2020-09-30' AS Date), N'Comment', N'S008', 1)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'3309202016531', N'1309202016249', N'caytamto@gmail.com', CAST(N'2020-09-30' AS Date), N'Comment', N'S008', 1)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'33092020165445', N'13092020165412', N'binz@gmail.com', CAST(N'2020-09-30' AS Date), N'Comment', N'S008', 1)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'33092020165532', N'13092020165412', N'binz@gmail.com', CAST(N'2020-09-30' AS Date), N'Comment', N'S008', 1)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'330920201679', N'13092020155846', N'caytamto@gmail.com', CAST(N'2020-09-30' AS Date), N'Comment', N'S008', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'4299202016158', N'117092020142100', N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-29' AS Date), N'Like', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'4299202016218', N'117092020140700', N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-29' AS Date), N'Like', N'S007', 1)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'42992020163332', N'117092020140900', N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-29' AS Date), N'Like', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'42992020201516', N'117092020142500', N'binz@gmail.com', CAST(N'2020-09-29' AS Date), N'Like', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'42992020201525', N'117092020142800', N'binz@gmail.com', CAST(N'2020-09-29' AS Date), N'Like', N'S007', 1)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'4299202020154', N'125092020112900', N'binz@gmail.com', CAST(N'2020-09-30' AS Date), N'Like', N'S007', 1)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'42992020201554', N'125092020112900', N'dinhtrithuc2021@gmail.com', CAST(N'2020-09-29' AS Date), N'Like', N'S007', 1)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'42992020201556', N'12509094000', N'dinhtrithuc2021@gmail.com', CAST(N'2020-09-29' AS Date), N'Like', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'4299202020158', N'12509094000', N'binz@gmail.com', CAST(N'2020-09-29' AS Date), N'Like', N'S007', 1)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'42992020201612', N'117092020141200', N'dinhtrithuc2021@gmail.com', CAST(N'2020-09-29' AS Date), N'Like', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'42992020201621', N'117092020141000', N'dinhtrithuc2021@gmail.com', CAST(N'2020-09-29' AS Date), N'Like', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'42992020201624', N'117092020142800', N'dinhtrithuc2021@gmail.com', CAST(N'2020-09-29' AS Date), N'Like', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'42992020201628', N'117092020141100', N'dinhtrithuc2021@gmail.com', CAST(N'2020-09-29' AS Date), N'Like', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'4299202020163', N'117092020140900', N'dinhtrithuc2021@gmail.com', CAST(N'2020-09-29' AS Date), N'Dislike', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'42992020201650', N'125092020112900', N'karik@gmail.com', CAST(N'2020-09-29' AS Date), N'Dislike', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'42992020201653', N'12509094000', N'karik@gmail.com', CAST(N'2020-09-29' AS Date), N'Like', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'42992020201656', N'117092020142100', N'karik@gmail.com', CAST(N'2020-09-29' AS Date), N'Like', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'42992020201659', N'117092020140700', N'karik@gmail.com', CAST(N'2020-09-29' AS Date), N'Dislike', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'4299202020175', N'117092020142400', N'karik@gmail.com', CAST(N'2020-09-29' AS Date), N'Like', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'4299202020178', N'117092020141500', N'karik@gmail.com', CAST(N'2020-09-29' AS Date), N'Like', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'42992020202632', N'1299202020220', N'binz@gmail.com', CAST(N'2020-09-29' AS Date), N'Like', N'S008', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'42992020204652', N'117092020142400', N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-29' AS Date), N'Like', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'43092020111010', N'117092020141200', N'tunhmse140357@fpt.edu.vn', CAST(N'2020-09-30' AS Date), N'Like', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'43092020111016', N'117092020142100', N'tunhmse140357@fpt.edu.vn', CAST(N'2020-09-30' AS Date), N'Dislike', N'S007', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'43092020111023', N'125092020112900', N'tunhmse140357@fpt.edu.vn', CAST(N'2020-09-30' AS Date), N'Like', N'S007', 1)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'43092020111314', N'1309202011134', N'hungnvse140996@fpt.edu.vn', CAST(N'2020-09-30' AS Date), N'Like', N'S008', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'43092020112940', N'13092020112910', N'binz@gmail.com', CAST(N'2020-09-30' AS Date), N'Like', N'S008', 1)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'4309202016526', N'1309202016249', N'caytamto@gmail.com', CAST(N'2020-09-30' AS Date), N'', N'S008', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'43092020165440', N'13092020165412', N'binz@gmail.com', CAST(N'2020-09-30' AS Date), N'Like', N'S008', 0)
INSERT [dbo].[Notification] ([notifyID], [articleID], [email], [date], [type], [status], [isRead]) VALUES (N'430920201674', N'13092020155846', N'caytamto@gmail.com', CAST(N'2020-09-30' AS Date), N'Like', N'S008', 0)
INSERT [dbo].[Status] ([statusID], [name]) VALUES (N'S001', N'New Account')
INSERT [dbo].[Status] ([statusID], [name]) VALUES (N'S002', N'Active Account')
INSERT [dbo].[Status] ([statusID], [name]) VALUES (N'S003', N'Delete Post')
INSERT [dbo].[Status] ([statusID], [name]) VALUES (N'S004', N'Active Post')
INSERT [dbo].[Status] ([statusID], [name]) VALUES (N'S005', N'Active Comment')
INSERT [dbo].[Status] ([statusID], [name]) VALUES (N'S006', N'Delete Comment')
INSERT [dbo].[Status] ([statusID], [name]) VALUES (N'S007', N'Active Notification')
INSERT [dbo].[Status] ([statusID], [name]) VALUES (N'S008', N'Delete Notification')
ALTER TABLE [dbo].[Account]  WITH CHECK ADD FOREIGN KEY([status])
REFERENCES [dbo].[Status] ([statusID])
GO
ALTER TABLE [dbo].[Article]  WITH CHECK ADD FOREIGN KEY([email])
REFERENCES [dbo].[Account] ([email])
GO
ALTER TABLE [dbo].[Article]  WITH CHECK ADD FOREIGN KEY([status])
REFERENCES [dbo].[Status] ([statusID])
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD FOREIGN KEY([articleID])
REFERENCES [dbo].[Article] ([articleID])
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD FOREIGN KEY([email])
REFERENCES [dbo].[Account] ([email])
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD FOREIGN KEY([status])
REFERENCES [dbo].[Status] ([statusID])
GO
ALTER TABLE [dbo].[Emotion]  WITH CHECK ADD FOREIGN KEY([articleID])
REFERENCES [dbo].[Article] ([articleID])
GO
ALTER TABLE [dbo].[Emotion]  WITH CHECK ADD FOREIGN KEY([email])
REFERENCES [dbo].[Account] ([email])
GO
ALTER TABLE [dbo].[Notification]  WITH CHECK ADD FOREIGN KEY([articleID])
REFERENCES [dbo].[Article] ([articleID])
GO
ALTER TABLE [dbo].[Notification]  WITH CHECK ADD FOREIGN KEY([email])
REFERENCES [dbo].[Account] ([email])
GO
ALTER TABLE [dbo].[Notification]  WITH CHECK ADD FOREIGN KEY([status])
REFERENCES [dbo].[Status] ([statusID])
GO
USE [master]
GO
ALTER DATABASE [Mini Social Network] SET  READ_WRITE 
GO
