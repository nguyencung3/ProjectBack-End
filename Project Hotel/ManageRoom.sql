USE [master]
GO
/****** Object:  Database [ManageRooms]    Script Date: 7/5/2017 8:15:07 PM ******/
CREATE DATABASE [ManageRooms]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ManageRooms', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\ManageRooms.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'ManageRooms_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\ManageRooms_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [ManageRooms] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ManageRooms].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ManageRooms] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ManageRooms] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ManageRooms] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ManageRooms] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ManageRooms] SET ARITHABORT OFF 
GO
ALTER DATABASE [ManageRooms] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ManageRooms] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [ManageRooms] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ManageRooms] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ManageRooms] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ManageRooms] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ManageRooms] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ManageRooms] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ManageRooms] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ManageRooms] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ManageRooms] SET  DISABLE_BROKER 
GO
ALTER DATABASE [ManageRooms] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ManageRooms] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ManageRooms] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ManageRooms] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ManageRooms] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ManageRooms] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ManageRooms] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ManageRooms] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [ManageRooms] SET  MULTI_USER 
GO
ALTER DATABASE [ManageRooms] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ManageRooms] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ManageRooms] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ManageRooms] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [ManageRooms]
GO
/****** Object:  Table [dbo].[tbl_account]    Script Date: 7/5/2017 8:15:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_account](
	[username] [varchar](20) NOT NULL,
	[password] [varchar](25) NOT NULL,
	[role] [int] NULL,
 CONSTRAINT [PK_tbl_account] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_maintenance]    Script Date: 7/5/2017 8:15:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_maintenance](
	[id] [int] NOT NULL,
	[requestDate] [datetime] NULL,
	[reason] [varchar](250) NULL,
	[repairedDate] [datetime] NULL,
	[mend] [varchar](250) NULL,
	[cost] [float] NULL,
	[roomId] [varchar](3) NULL,
 CONSTRAINT [PK_tbl_maintenance] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_room]    Script Date: 7/5/2017 8:15:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_room](
	[roomID] [varchar](3) NOT NULL,
	[description] [varchar](250) NULL,
	[hourPrice] [float] NULL,
	[dayPrice] [float] NULL,
	[categoryID] [varchar](5) NULL,
 CONSTRAINT [PK_tbl_room] PRIMARY KEY CLUSTERED 
(
	[roomID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_roomCategory]    Script Date: 7/5/2017 8:15:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_roomCategory](
	[categoryID] [varchar](5) NULL,
	[categoryName] [varchar](30) NULL,
	[description] [varchar](50) NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tbl_account] ([username], [password], [role]) VALUES (N'admin', N'123', 0)
INSERT [dbo].[tbl_account] ([username], [password], [role]) VALUES (N'cung', N'123', 3)
INSERT [dbo].[tbl_account] ([username], [password], [role]) VALUES (N'manager', N'123', 3)
INSERT [dbo].[tbl_account] ([username], [password], [role]) VALUES (N'staff', N'123', 1)
INSERT [dbo].[tbl_account] ([username], [password], [role]) VALUES (N'user', N'123', 2)
INSERT [dbo].[tbl_account] ([username], [password], [role]) VALUES (N'vu', N'123', 1)
INSERT [dbo].[tbl_maintenance] ([id], [requestDate], [reason], [repairedDate], [mend], [cost], [roomId]) VALUES (1, CAST(N'2017-07-05 08:04:54.000' AS DateTime), N'Hu bàn ', NULL, NULL, NULL, N'101')
INSERT [dbo].[tbl_maintenance] ([id], [requestDate], [reason], [repairedDate], [mend], [cost], [roomId]) VALUES (2, CAST(N'2017-07-05 08:04:54.000' AS DateTime), N'may lanh k lanh', NULL, NULL, NULL, N'102')
INSERT [dbo].[tbl_maintenance] ([id], [requestDate], [reason], [repairedDate], [mend], [cost], [roomId]) VALUES (3, CAST(N'2017-07-05 08:04:54.000' AS DateTime), N'Chua vs phòng', NULL, NULL, NULL, N'103')
INSERT [dbo].[tbl_room] ([roomID], [description], [hourPrice], [dayPrice], [categoryID]) VALUES (N'101', N'phong 2 nguoi do', 5, 110, N'2')
INSERT [dbo].[tbl_room] ([roomID], [description], [hourPrice], [dayPrice], [categoryID]) VALUES (N'102', N'phong 3 nguoi', 15, 150, N'1')
INSERT [dbo].[tbl_room] ([roomID], [description], [hourPrice], [dayPrice], [categoryID]) VALUES (N'103', N'phong don ', 13, 200, N'2')
INSERT [dbo].[tbl_room] ([roomID], [description], [hourPrice], [dayPrice], [categoryID]) VALUES (N'104', N'phong don ', 20, 250, N'3')
INSERT [dbo].[tbl_room] ([roomID], [description], [hourPrice], [dayPrice], [categoryID]) VALUES (N'105', N'phong 2 nguoi', 20, 300, N'3')
INSERT [dbo].[tbl_room] ([roomID], [description], [hourPrice], [dayPrice], [categoryID]) VALUES (N'106', N'phong 2 nguoi - huong bien', 60, 320, N'3')
INSERT [dbo].[tbl_roomCategory] ([categoryID], [categoryName], [description]) VALUES (N'1', N'Phong Vip', N'Phong danh cho khach cao cap')
INSERT [dbo].[tbl_roomCategory] ([categoryID], [categoryName], [description]) VALUES (N'2', N'Phong Normal', N'Phong danh cho khach thuong')
INSERT [dbo].[tbl_roomCategory] ([categoryID], [categoryName], [description]) VALUES (N'3', N'Phong SuperVip', N'Phong sieu sang')
INSERT [dbo].[tbl_roomCategory] ([categoryID], [categoryName], [description]) VALUES (N'4', N'Phong Gia Dinh', N'Phong danh cho tap the')
USE [master]
GO
ALTER DATABASE [ManageRooms] SET  READ_WRITE 
GO
