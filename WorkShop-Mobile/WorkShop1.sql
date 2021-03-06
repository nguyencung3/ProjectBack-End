USE [master]
GO
/****** Object:  Database [WorkShop1 ]    Script Date: 6/15/2017 3:45:54 PM ******/
CREATE DATABASE [WorkShop1 ]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'WorkShop1', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\WorkShop1 .mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'WorkShop1 _log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\WorkShop1 _log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [WorkShop1 ] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [WorkShop1 ].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [WorkShop1 ] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [WorkShop1 ] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [WorkShop1 ] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [WorkShop1 ] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [WorkShop1 ] SET ARITHABORT OFF 
GO
ALTER DATABASE [WorkShop1 ] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [WorkShop1 ] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [WorkShop1 ] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [WorkShop1 ] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [WorkShop1 ] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [WorkShop1 ] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [WorkShop1 ] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [WorkShop1 ] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [WorkShop1 ] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [WorkShop1 ] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [WorkShop1 ] SET  DISABLE_BROKER 
GO
ALTER DATABASE [WorkShop1 ] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [WorkShop1 ] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [WorkShop1 ] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [WorkShop1 ] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [WorkShop1 ] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [WorkShop1 ] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [WorkShop1 ] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [WorkShop1 ] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [WorkShop1 ] SET  MULTI_USER 
GO
ALTER DATABASE [WorkShop1 ] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [WorkShop1 ] SET DB_CHAINING OFF 
GO
ALTER DATABASE [WorkShop1 ] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [WorkShop1 ] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [WorkShop1 ]
GO
/****** Object:  Table [dbo].[tbl_Mobile]    Script Date: 6/15/2017 3:45:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_Mobile](
	[mobileId] [varchar](10) NOT NULL,
	[description] [varchar](250) NOT NULL,
	[price] [float] NULL,
	[mobileName] [varchar](20) NOT NULL,
	[yearOfProduction] [int] NULL,
	[quantity] [int] NULL,
	[notSale] [bit] NULL,
 CONSTRAINT [PK_tblMobile] PRIMARY KEY CLUSTERED 
(
	[mobileId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_OrderDetail]    Script Date: 6/15/2017 3:45:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_OrderDetail](
	[orderId] [int] NOT NULL,
	[userId] [varchar](20) NULL,
	[mobileId] [varchar](10) NULL,
	[quantity] [int] NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_User]    Script Date: 6/15/2017 3:45:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_User](
	[userId] [varchar](20) NOT NULL,
	[password] [int] NOT NULL,
	[fullName] [varchar](50) NOT NULL,
	[role] [int] NULL,
 CONSTRAINT [PK_tbl_User] PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tbl_Mobile] ([mobileId], [description], [price], [mobileName], [yearOfProduction], [quantity], [notSale]) VALUES (N'122', N'phone', 10.5, N'iphone6', 2015, 98, 0)
INSERT [dbo].[tbl_Mobile] ([mobileId], [description], [price], [mobileName], [yearOfProduction], [quantity], [notSale]) VALUES (N'123', N'123', 9, N'iphone7', 2016, 100, 0)
INSERT [dbo].[tbl_Mobile] ([mobileId], [description], [price], [mobileName], [yearOfProduction], [quantity], [notSale]) VALUES (N'124', N'tablet', 12, N'samsung 10.1 inch', 2014, 86, 0)
INSERT [dbo].[tbl_Mobile] ([mobileId], [description], [price], [mobileName], [yearOfProduction], [quantity], [notSale]) VALUES (N'126', N'phone', 15, N'iphone7s', 2017, 100, 1)
INSERT [dbo].[tbl_Mobile] ([mobileId], [description], [price], [mobileName], [yearOfProduction], [quantity], [notSale]) VALUES (N'180', N'tablet', 25, N'Ipad Air2', 2015, 20, 1)
INSERT [dbo].[tbl_User] ([userId], [password], [fullName], [role]) VALUES (N'admin', 123, N'cung nguyen', 1)
INSERT [dbo].[tbl_User] ([userId], [password], [fullName], [role]) VALUES (N'hong', 123, N'anh hong', 0)
INSERT [dbo].[tbl_User] ([userId], [password], [fullName], [role]) VALUES (N'nhan', 123, N'nhan', 1)
INSERT [dbo].[tbl_User] ([userId], [password], [fullName], [role]) VALUES (N'staff', 123, N'nhan', 2)
INSERT [dbo].[tbl_User] ([userId], [password], [fullName], [role]) VALUES (N'user', 123, N'cam', 0)
INSERT [dbo].[tbl_User] ([userId], [password], [fullName], [role]) VALUES (N'vu', 123, N'vu', 0)
USE [master]
GO
ALTER DATABASE [WorkShop1 ] SET  READ_WRITE 
GO
