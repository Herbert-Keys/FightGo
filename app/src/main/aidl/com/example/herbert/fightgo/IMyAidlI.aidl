// IMyAidlI.aidl
package com.example.herbert.fightgo;
import com.example.herbert.fightgo.bean.Person;
// Declare any non-default types here with import statements

interface IMyAidlI {
    void addPerson(in Person person);
    List<Person> getPersonList();
}
