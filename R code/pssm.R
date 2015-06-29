#setwd("d:/analysis")
index<-2
type="sigma"

#define related function
fun.getColnames <- function()
{
  name<-"A"
  name[20]<-"V"
  name[1]<-"A"
  name[2]<-"R"
  name[3]<-"N"
  name[4]<-"D"
  name[5]<-"C"
  name[6]<-"Q"
  name[7]<-"E"
  name[8]<-"G"
  name[9]<-"H"
  name[10]<-"I"
  name[11]<-"L"
  name[12]<-"K"
  name[13]<-"M"
  name[14]<-"F"
  name[15]<-"P"
  name[16]<-"S"
  name[17]<-"T"
  name[18]<-"W"
  name[19]<-"Y"
  return(name)
}

#get the source data
tablel<-read.csv("result-left.csv")
tabler<-read.csv("result-right.csv")

#init data
pssm<-1:120
dim(pssm)<-c(6,20)
pssm<-as.data.frame(pssm)
name<-fun.getColnames()
colnames(pssm)<-name[1:20]

#statistic data   left
sigma<-tablel[c(1,index)]
newsigma<-sigma[order(sigma[,2],decreasing=TRUE),]
residues<-"R"
residues[1500]<-"R"
dim(residues)<-c(500,3)
values<-1:500
dim(values)<-c(500,1)
for(i in 1:500)
{
  tempseq=as.character(newsigma[[i,1]])
  tempseq<-strsplit(tempseq,split=NULL)
  tempseq<-tempseq[[1]]
  residues[i,1]<-tempseq[1]
  residues[i,2]<-tempseq[2]
  residues[i,3]<-tempseq[3]
  if(newsigma[[i,2]]>0)
    values[i]<-newsigma[[i,2]]
  else
    values[i]<-0
}
data<-data.frame(AA=residues,V=values)

#statis every AA left
for(j in 1:3)
{
  for(i in 1:20)
  {
    aa<-names(pssm)[i]
    sum<-0
    num<-0;
    for(m in 1:500)
    {
      if(residues[m,j]==aa)
      {
        sum<-sum+values[m]
        num<-num+1
      }
    }
    if(num==0)
      pssm[j,aa]<-0
    else
    {
      average<-sum/num
      pssm[j,aa]<-average
    }
  }
}

#statistic data right
sigma<-tabler[c(1,index)]
newsigma<-sigma[order(sigma[,2],decreasing=TRUE),]
residues<-"R"
residues[1500]<-"R"
dim(residues)<-c(500,3)
values<-1:500
dim(values)<-c(500,1)
for(i in 1:500)
{
  tempseq=as.character(newsigma[[i,1]])
  tempseq<-strsplit(tempseq,split=NULL)
  tempseq<-tempseq[[1]]
  residues[i,1]<-tempseq[1]
  residues[i,2]<-tempseq[2]
  residues[i,3]<-tempseq[3]
  if(newsigma[[i,2]]>0)
    values[i]<-newsigma[[i,2]]
  else
    values[i]<-0
}
data<-data.frame(AA=residues,V=values)

#statis every AA left
for(j in 1:3)
{
  for(i in 1:20)
  {
    aa<-names(pssm)[i]
    sum<-0
    num<-0;
    for(m in 1:500)
    {
      if(residues[m,j]==aa)
      {
        sum<-sum+values[m]
        num<-num+1
      }
    }
    if(num==0)
      pssm[j+3,aa]<-0
    else
    {
      average<-sum/num
      pssm[j+3,aa]<-average
    }
  }
}

outname<-paste("d:/",type,"-pssm.txt",sep="")
write.table(pssm, file = outname, row.names = F, quote = F)
