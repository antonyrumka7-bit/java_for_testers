package model;

public record ContactData(String id, String firstname, String middlename, String lastname, String nickname, String title,
                          String company, String address, String home, String mobile, String work,
                          String email, String email2, String email3, String photo) {

    public ContactData(){
        this("", "","","","","","","","","","","","","","");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(this.id, firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withMiddlename(String middlename ) {
        return new ContactData(this.id, firstname, middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(this.id, this.firstname, this.middlename, lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withNickname(String nickname) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withTitle(String title) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.nickname, title, this.company, this.address, this.home, this.mobile, this.work, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withCompany(String company) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.title, company, this.address, this.home, this.mobile, this.work, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, address, this.home, this.mobile, this.work, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, home, this.mobile, this.work, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData("", this.firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, mobile, this.work, this.email, this.email2, this.email3, this.photo);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, mobile, work, this.email, this.email2, this.email3, this.photo);
    }


    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, email, this.email2, this.email3, this.photo);
    }


    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, mobile, this.work, this.email, email2, this.email3, this.photo);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, mobile, this.work, this.email, this.email2, email3, this.photo);
    }

    public ContactData withphoto(String photo) {
        return new ContactData(this.id, this.firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, mobile, this.work, this.email, this.email2, this.email3, photo);
    }
}