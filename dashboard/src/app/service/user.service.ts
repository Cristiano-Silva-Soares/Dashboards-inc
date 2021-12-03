import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MirroredUser } from '../models/MirroredUser';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(

    private http: HttpClient

  ) { }

  //Consume Methods: 

  public getUsers(id: number): Observable<User> {
    return this.http.get <User> (`https://dashback.herokuapp.com/users/id/${id}`)
  }

  public login(userLogin: MirroredUser): Observable<MirroredUser> {
    return this.http.put <MirroredUser> (`https://dashback.herokuapp.com/users/login`, userLogin)
  }

  public register(user: User): Observable<User> {
    return this.http.post <User> (`https://dashback.herokuapp.com/users/login`, user)
  }
}
