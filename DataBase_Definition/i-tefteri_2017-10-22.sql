USE [master]
GO
/****** Object:  Database [i-tefteri]    Script Date: 22/10/2017 16:21:26 ******/
CREATE DATABASE [i-tefteri]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'i-tefteri', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.SQLEXPRESS2016\MSSQL\DATA\i-tefteri.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'i-tefteri_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.SQLEXPRESS2016\MSSQL\DATA\i-tefteri_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [i-tefteri] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [i-tefteri].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [i-tefteri] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [i-tefteri] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [i-tefteri] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [i-tefteri] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [i-tefteri] SET ARITHABORT OFF 
GO
ALTER DATABASE [i-tefteri] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [i-tefteri] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [i-tefteri] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [i-tefteri] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [i-tefteri] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [i-tefteri] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [i-tefteri] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [i-tefteri] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [i-tefteri] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [i-tefteri] SET  DISABLE_BROKER 
GO
ALTER DATABASE [i-tefteri] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [i-tefteri] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [i-tefteri] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [i-tefteri] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [i-tefteri] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [i-tefteri] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [i-tefteri] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [i-tefteri] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [i-tefteri] SET  MULTI_USER 
GO
ALTER DATABASE [i-tefteri] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [i-tefteri] SET DB_CHAINING OFF 
GO
ALTER DATABASE [i-tefteri] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [i-tefteri] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [i-tefteri] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [i-tefteri] SET QUERY_STORE = OFF
GO
USE [i-tefteri]
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [i-tefteri]
GO
/****** Object:  Table [dbo].[i_tefteri_Dosh]    Script Date: 22/10/2017 16:21:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[i_tefteri_Dosh](
	[DOSH_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[TransactionID] [bigint] NOT NULL,
	[HMEROMHNIA_EKT] [date] NOT NULL,
	[POSO] [money] NOT NULL,
	[PLHRW8HKE] [tinyint] NULL,
	[STATUS] [tinyint] NOT NULL,
 CONSTRAINT [PK_i_tefteri_Dosh] PRIMARY KEY CLUSTERED 
(
	[DOSH_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[i_tefteri_DOSH_Status]    Script Date: 22/10/2017 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[i_tefteri_DOSH_Status](
	[Status_ID] [int] NOT NULL,
	[Status_Descr] [varchar](50) NOT NULL,
 CONSTRAINT [PK_i_tefteri_DOSH_Status] PRIMARY KEY CLUSTERED 
(
	[Status_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[i_tefteri_Log_File]    Script Date: 22/10/2017 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[i_tefteri_Log_File](
	[Log_File_ID] [bigint] IDENTITY(1,1) NOT NULL,
	[Log_File_Type] [tinyint] NOT NULL,
	[Log_Date] [date] NOT NULL,
	[Log_Time] [time](7) NOT NULL,
	[Log_File_Text] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_i_tefteri_Log_File] PRIMARY KEY CLUSTERED 
(
	[Log_File_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[i_tefteri_Log_File_Types]    Script Date: 22/10/2017 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[i_tefteri_Log_File_Types](
	[Log_File_Type] [tinyint] NOT NULL,
	[Log_File_Type_Descr] [varchar](50) NOT NULL,
 CONSTRAINT [PK_i_tefteri_Log_File_Types] PRIMARY KEY CLUSTERED 
(
	[Log_File_Type] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[i_tefteri_Persons]    Script Date: 22/10/2017 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[i_tefteri_Persons](
	[userID] [int] IDENTITY(1,1) NOT NULL,
	[iBankuserID] [varchar](50) NOT NULL,
	[Mobile_Num] [varchar](12) NOT NULL,
	[STATUS] [tinyint] NOT NULL,
	[BASIKOS_LOGAR] [varchar](50) NOT NULL,
 CONSTRAINT [PK_i-tefteri_Persons] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[i_tefteri_Persons_Status]    Script Date: 22/10/2017 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[i_tefteri_Persons_Status](
	[Person_Status_ID] [tinyint] NOT NULL,
	[Status] [varchar](50) NOT NULL,
 CONSTRAINT [PK_i_tefteri_Persons_Status] PRIMARY KEY CLUSTERED 
(
	[Person_Status_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[i_tefteri_Transaction_Status]    Script Date: 22/10/2017 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[i_tefteri_Transaction_Status](
	[Status_ID] [tinyint] NOT NULL,
	[Status_Descr] [varchar](50) NOT NULL,
 CONSTRAINT [PK_i_tefteri_Transaction_Status] PRIMARY KEY CLUSTERED 
(
	[Status_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[i_tefteri_Transactions]    Script Date: 22/10/2017 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[i_tefteri_Transactions](
	[TransactionID] [bigint] IDENTITY(1,1) NOT NULL,
	[userID_AGORASTH] [int] NOT NULL,
	[userID_PWLHTH] [int] NOT NULL,
	[HMEROMHNIA] [datetime] NOT NULL,
	[SXOLIO_PWLHTH] [nvarchar](max) NOT NULL,
	[SXOLIO_AGORASTH] [nvarchar](max) NULL,
	[POSO] [money] NOT NULL,
	[DOSEIS] [int] NOT NULL,
	[STATUS] [tinyint] NOT NULL,
 CONSTRAINT [PK_i_tefteri_Transactions] PRIMARY KEY CLUSTERED 
(
	[TransactionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
ALTER TABLE [dbo].[i_tefteri_Log_File] ADD  CONSTRAINT [DF_i_tefteri_Log_File_Log_Date]  DEFAULT (getdate()) FOR [Log_Date]
GO
ALTER TABLE [dbo].[i_tefteri_Log_File] ADD  CONSTRAINT [DF_i_tefteri_Log_File_Log_Time]  DEFAULT (getdate()) FOR [Log_Time]
GO
ALTER TABLE [dbo].[i_tefteri_Transactions] ADD  CONSTRAINT [DF_i_tefteri_Transactions_HMEROMHNIA]  DEFAULT (getdate()) FOR [HMEROMHNIA]
GO
/****** Object:  StoredProcedure [dbo].[i_tefteri_sp_Get_Active_Users]    Script Date: 22/10/2017 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[i_tefteri_sp_Get_Active_Users] 
@What2Do	as varchar(50)
AS
BEGIN
		if @What2Do='All Person'
		Begin
			Select
					UserID,
					iBankuserID,
					BASIKOS_LOGAR
			from
					i_tefteri_Persons
			where
					STATUS=1
		End
		Else
		Begin
			Select
					UserID,
					iBankuserID,
					BASIKOS_LOGAR
			from
					i_tefteri_Persons
			where
					iBankuserID=@What2Do
			and
					STATUS=1
		End
END

GO
/****** Object:  StoredProcedure [dbo].[i_tefteri_sp_Get_Collection]    Script Date: 22/10/2017 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[i_tefteri_sp_Get_Collection]
@UserID		as int
AS
BEGIN
	Select
			Persons.UserID,
			Persons.iBankuserID,
			Trans.HMEROMHNIA,
			HMEROMHNIA_EKT=(Select top 1 HMEROMHNIA_EKT from i_tefteri_Dosh where TransactionID=Trans.TransactionID),
			Trans.POSO,
			Trans.DOSEIS,
			Trans.TransactionID
	from
			i_tefteri_Transactions Trans	left outer join i_tefteri_Persons Persons
											on Trans.userID_PWLHTH=Persons.userID
	where
			Trans.userID_AGORASTH=@UserID
	and
			Trans.STATUS=1
	order by
			Trans.TransactionID desc
END

GO
/****** Object:  StoredProcedure [dbo].[i_tefteri_sp_Log_File]    Script Date: 22/10/2017 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[i_tefteri_sp_Log_File]
@What2Do		as varchar(20),
@Log_File_Type	as tinyint,
@Log_File_Text	as nvarchar(max)
AS
BEGIN
	if @What2Do='Insert Into Log'
	Begin
		Insert Into i_tefteri_Log_File 
			(Log_File_Type,Log_File_Text)
		values
			(@Log_File_Type,@Log_File_Text)
	End
END

GO
/****** Object:  StoredProcedure [dbo].[i_tefteri_sp_New_Collection]    Script Date: 22/10/2017 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[i_tefteri_sp_New_Collection]
@userID_AGORASTH	as int,
@userID_PWLHTH		as int,
@SXOLIO_PWLHTH		as nvarchar(max),
@POSO				as money,
@DOSEIS				as int,
@STATUS				as int,
@Transaction_id		as bigint output
AS
BEGIN
	Insert Into i_tefteri_Transactions
	(userID_AGORASTH,userID_PWLHTH,SXOLIO_PWLHTH,SXOLIO_AGORASTH,POSO,DOSEIS,STATUS)
	values
	(@userID_AGORASTH,@userID_PWLHTH,@SXOLIO_PWLHTH,null,@POSO,@DOSEIS,@STATUS)
	SELECT @Transaction_id=SCOPE_IDENTITY()
END

GO
/****** Object:  StoredProcedure [dbo].[i_tefteri_sp_New_Collection_DOSH]    Script Date: 22/10/2017 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[i_tefteri_sp_New_Collection_DOSH]
@TransactionID		as BigInt,
@HMEROMHNIA_EKT		as Date,
@POSO				as money
AS
BEGIN
	Insert Into i_tefteri_Dosh
	(TransactionID,HMEROMHNIA_EKT,POSO,PLHRW8HKE,STATUS)
	values
	(@TransactionID,@HMEROMHNIA_EKT,@POSO,null,1)
END

GO
/****** Object:  StoredProcedure [dbo].[i_tefteri_sp_Update_Collection]    Script Date: 22/10/2017 16:21:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[i_tefteri_sp_Update_Collection]
@Transaction_id		as bigint,
@SXOLIO_AGORASTH	as nvarchar(max)
AS
BEGIN
	update i_tefteri_Transactions set
										STATUS=2,
										SXOLIO_AGORASTH=@SXOLIO_AGORASTH
	where
			TransactionID=@Transaction_id
END

GO
USE [master]
GO
ALTER DATABASE [i-tefteri] SET  READ_WRITE 
GO
