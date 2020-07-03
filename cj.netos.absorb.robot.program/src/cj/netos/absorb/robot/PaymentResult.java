package cj.netos.absorb.robot;


public class PaymentResult extends PayRecord {
    PayDetails details;

    public PayDetails getDetails() {
        return details;
    }

    public void setDetails(PayDetails details) {
        this.details = details;
    }

    public void load(PayRecord record) {
        this.setAmount(record.getAmount());
        this.setCtime(record.getCtime());
        this.setCurrency(record.getCurrency());
        this.setLutime(record.getLutime());
        this.setMessage(record.getMessage());
        this.setNote(record.getNote());
        this.setPerson(record.getPerson());
        this.setPersonName(record.getPersonName());
        this.setSn((record.getSn()));
        this.setState(record.getState());
        this.setStatus(record.getStatus());
        this.setType(record.getType());
    }
}
